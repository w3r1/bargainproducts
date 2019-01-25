package com.johnlewis.api.bargainproducts.converter;

import com.johnlewis.api.bargainproducts.domain.LabelType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class LabelTypeConverter implements Converter<String, LabelType> {

    @Override
    public LabelType convert(String s) {

        return LabelType.valueOf(s);
    }
}