package com.johnlewis.api.bargainproducts.domain;

import java.util.HashMap;
import java.util.Map;

public class RGBColorMap {

    public static final Map<String, String> COLOR_NAME_TO_RGB;

    static {
        COLOR_NAME_TO_RGB = new HashMap<>();
        COLOR_NAME_TO_RGB.put("black", "000000");
        COLOR_NAME_TO_RGB.put("silver", "C0C0C0");
        COLOR_NAME_TO_RGB.put("gray", "808080");
        COLOR_NAME_TO_RGB.put("grey", "808080");
        COLOR_NAME_TO_RGB.put("white", "FFFFFF");
        COLOR_NAME_TO_RGB.put("maroon", "800000");
        COLOR_NAME_TO_RGB.put("red", "FF0000");
        COLOR_NAME_TO_RGB.put("purple", "800080");
        COLOR_NAME_TO_RGB.put("fuchsia", "FF00FF");
        COLOR_NAME_TO_RGB.put("green", "008000");
        COLOR_NAME_TO_RGB.put("lime", "00FF00");
        COLOR_NAME_TO_RGB.put("olive", "808000");
        COLOR_NAME_TO_RGB.put("yellow", "FFFF00");
        COLOR_NAME_TO_RGB.put("navy", "000080");
        COLOR_NAME_TO_RGB.put("blue", "0000FF");
        COLOR_NAME_TO_RGB.put("teal", "008080");
        COLOR_NAME_TO_RGB.put("aqua", "00FFFF");
        COLOR_NAME_TO_RGB.put("pink", "FFC0CB");
        COLOR_NAME_TO_RGB.put("orange", "FFA500");
    }
}
