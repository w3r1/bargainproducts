package com.johnlewis.api.bargainproducts.service;

import com.johnlewis.api.bargainproducts.client.RawProductsConsumerClient;
import com.johnlewis.api.bargainproducts.client.domain.RawProduct;
import com.johnlewis.api.bargainproducts.domain.ColorSwatch;
import com.johnlewis.api.bargainproducts.domain.LabelType;
import com.johnlewis.api.bargainproducts.domain.Product;
import io.vavr.control.Try;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static com.johnlewis.api.bargainproducts.domain.LabelType.ShowPercDscount;
import static com.johnlewis.api.bargainproducts.domain.LabelType.ShowWasThenNow;
import static com.johnlewis.api.bargainproducts.domain.RGBColorMap.COLOR_NAME_TO_RGB;
import static java.util.logging.Level.WARNING;
import static org.apache.commons.lang3.StringUtils.*;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger LOGGER = Logger.getLogger(ProductServiceImpl.class.getName());

    @Autowired
    private RawProductsConsumerClient rawProductsConsumerClient;

    @Override
    public List<Product> getWomensDressesSortedByHighestDiscount(final LabelType labelType) {

        return rawProductsConsumerClient.getWomensDresses()
                .stream()
                .filter(p -> isNotBlank(p.getPrice().getWas()))
                .map(p -> mapToProduct(p, labelType))
                .sorted()
                .collect(Collectors.toList());
    }

    private Product mapToProduct(final RawProduct rawProduct,
                                 final LabelType labelType) {

        Product product = Product.builder()
                .productId(rawProduct.getProductId())
                .title(rawProduct.getTitle())
                .build();

        setPrices(product, rawProduct);
        setPriceLabel(product, labelType);
        setVariants(product, rawProduct);

        return product;
    }

    private void setVariants(Product product, RawProduct rawProduct) {

        List<ColorSwatch> colorList = rawProduct.getColorSwatches()
                .stream()
                .map(c -> {
                    String rgbColor = COLOR_NAME_TO_RGB.get(c.getBasicColor().trim().toLowerCase());

                    return ColorSwatch.builder()
                            .color(c.getColor())
                            .skuid(c.getSkuId())
                            .rgbColor(rgbColor != null ? rgbColor : c.getBasicColor())
                            .build();
                })
                .collect(Collectors.toList());

        product.setColorSwatches(colorList);
    }

    private void setPriceLabel(final Product product,
                               final LabelType labelType) {

        String priceLabel;
        if (ShowWasThenNow.equals(labelType)) {
            String then = EMPTY;
            if (product.getThenPrice() != null) {
                then = join(", then ", product.getThenPrice());
            }
            priceLabel = join("Was ", product.getWasPrice(), then, ", now ", product.getNowPrice());
        } else if (ShowPercDscount.equals(labelType)) {
            priceLabel = join(String.valueOf(product.getPriceDiscountPerc().intValue()),
                    "% off - now ", product.getNowPrice());
        } else {
            priceLabel = join("Was ", product.getWasPrice(), ", now ", product.getNowPrice());
        }

        product.setPriceLabel(priceLabel);
    }

    private void setPrices(final Product product,
                           final RawProduct rawProduct) {

        final String currency = rawProduct.getPrice().getCurrency();

        float wasPrice = getWasPrice(rawProduct);
        product.setWasPrice(toPriceWithCurrency(wasPrice, currency));

        float nowPrice = getNowPrice(rawProduct);
        product.setNowPrice(toPriceWithCurrency(nowPrice, currency));
        product.setPriceReduction(wasPrice - nowPrice);
        product.setPriceDiscountPerc(100f - (100f / wasPrice) * nowPrice);

        Optional.ofNullable(getThenPrice(rawProduct))
                .ifPresent(p -> product.setThenPrice(toPriceWithCurrency(p, currency)));
    }

    private float getWasPrice(final RawProduct rawProduct) {

        String productId = rawProduct.getProductId();

        String wasPriceString = Optional
                .of(rawProduct.getPrice().getWas())
                .filter(w -> isNotEmpty(w))
                .orElseGet(() -> "0");
        Float wasPrice = toFloatPrice(wasPriceString, productId);

        return wasPrice;
    }

    private Float toFloatPrice(final String priceString,
                               final String productId) {

        return Try.of(() -> Float.valueOf(priceString))
                .onFailure((e) -> LOGGER.log(WARNING, join("price NaN ", priceString," for product ", productId)))
                .toJavaOptional()
                .orElse(0f);
    }

    private float getNowPrice(final RawProduct rawProduct) {

        String productId = rawProduct.getProductId();

        String nowPriceString = (String) Optional
                .of(rawProduct.getPrice().getNow())
                .filter(n -> n instanceof String)
                .orElseGet(() -> ((Map) rawProduct.getPrice().getNow()).get("to")); // Decided to take higher value
        Float nowPrice = toFloatPrice(nowPriceString, productId);

        return nowPrice;
    }

    private Float getThenPrice(final RawProduct rawProduct) {

        String productId = rawProduct.getProductId();

        if (isNotEmpty(rawProduct.getPrice().getThen2())) {

            String thenPriceString = rawProduct.getPrice().getThen2();
            return toFloatPrice(thenPriceString, productId);
        } else if (isNotEmpty(rawProduct.getPrice().getThen1())) {

            String thenPriceString = rawProduct.getPrice().getThen1();
            return toFloatPrice(thenPriceString, productId);
        }

        return null;
    }

    private String toPriceWithCurrency(final Float price,
                                       final String currency) {

        String currencySign;
        switch (currency) {

            case "GBP":
                currencySign = "£";
                break;

            case "USD":
                currencySign = "$";
                break;

            case "EUR":
                currencySign = "€";
                break;

            default:
                currencySign = currency;
                break;
        }

        boolean isPriceLower10 = price < 10;
        if (isPriceLower10) {
            return join(currencySign, String.format("%.2f", price));
        } else {
            return join(currencySign, String.valueOf(price.intValue()));
        }
    }
}
