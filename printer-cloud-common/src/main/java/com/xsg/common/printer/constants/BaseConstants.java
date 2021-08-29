package com.xsg.common.printer.constants;

import com.xsg.common.printer.constants.FontSizeEnum;
import com.xsg.common.printer.model.FontDrawSize;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 高总辉
 * @desc
 * @date 2020/11/23 2:36 下午
 */
public class BaseConstants {

    public static final Map<String, Color> colorMap = new HashMap<>();

    public static final Map<String, String> fontNameMap = new HashMap<>();

    public static final Map<FontSizeEnum, FontDrawSize> imageFontDrawSizeMap = new HashMap<>();

    public static final Map<FontSizeEnum, FontDrawSize> tspLFontDrawSizeMap = new HashMap<>();

    public static final Map<FontSizeEnum, FontDrawSize> cpcLFontDrawSizeMap = new HashMap<>();

    public static final Map<FontSizeEnum, FontDrawSize> tsplHrptFontDrawSizeMap = new HashMap<>();

    static {
        colorMap.put("1", Color.WHITE);
        colorMap.put("2", Color.BLACK);
        colorMap.put("3", Color.BLUE);

        fontNameMap.put("1", "黑体");
        fontNameMap.put("2", "宋体");
        fontNameMap.put("3", "微软雅黑");

//        tspLFontDrawSizeMap.put(FontSizeEnum.SMALL, FontDrawSize.of(FontSizeEnum.SMALL, 10, 16, 16,16, 10, 0, "TSS16.BF2"));
        tspLFontDrawSizeMap.put(FontSizeEnum.SMALL, FontDrawSize.of(FontSizeEnum.SMALL, 13, 25, 24,24, 13, 0, "TSS24.BF2"));
        tspLFontDrawSizeMap.put(FontSizeEnum.MIDDLE, FontDrawSize.of(FontSizeEnum.MIDDLE, 13, 25, 24,24, 13, 0, "TSS24.BF2"));
        tspLFontDrawSizeMap.put(FontSizeEnum.BIG, FontDrawSize.of(FontSizeEnum.BIG, 13, 34, 32,32, 13, 0, "TSS32.BF2"));


//        cpcLFontDrawSizeMap.put(FontSizeEnum.SMALL, FontDrawSize.of(FontSizeEnum.SMALL, 8, 16, 16,16, 8, 0, "55"));
        cpcLFontDrawSizeMap.put(FontSizeEnum.SMALL, FontDrawSize.of(FontSizeEnum.SMALL, 13, 25, 16,24, 13, 0, "11"));
        cpcLFontDrawSizeMap.put(FontSizeEnum.MIDDLE, FontDrawSize.of(FontSizeEnum.MIDDLE, 13, 25, 16,24, 13, 0, "11"));
        cpcLFontDrawSizeMap.put(FontSizeEnum.BIG, FontDrawSize.of(FontSizeEnum.BIG, 15, 32, 32,32, 15, 0, "55"));

        tsplHrptFontDrawSizeMap.put(FontSizeEnum.SMALL, FontDrawSize.of(FontSizeEnum.SMALL, 8, 15, 15,15, 8, 0, "15"));
        tsplHrptFontDrawSizeMap.put(FontSizeEnum.MIDDLE, FontDrawSize.of(FontSizeEnum.MIDDLE, 10, 25, 25,24, 10, 0, "24"));
        tsplHrptFontDrawSizeMap.put(FontSizeEnum.BIG, FontDrawSize.of(FontSizeEnum.BIG, 16, 33, 33,33, 16, 0, "32"));
    }

    /**
     * 获取颜色
     * @param color
     * @return
     */
    public static Color getColor (int color) {
        return BaseConstants.colorMap.get(String.valueOf(color));
    }

    /**
     * 获取字体
     * @param fontNameType
     * @return
     */
    public static String getFontName (int fontNameType) {
        return BaseConstants.fontNameMap.get(String.valueOf(fontNameType));
    }

    public static FontDrawSize getTsplFontDrawSize(FontSizeEnum fontSizeEnum) {
        return BaseConstants.tspLFontDrawSizeMap.get(fontSizeEnum);
    }

    public static FontDrawSize getCpclFontDrawSize(FontSizeEnum fontSizeEnum) {
        return BaseConstants.cpcLFontDrawSizeMap.get(fontSizeEnum);
    }

    public static FontDrawSize getTsplHrptFontDrawSize(FontSizeEnum fontSizeEnum) {
        return BaseConstants.tsplHrptFontDrawSizeMap.get(fontSizeEnum);
    }

    public static FontDrawSize getImageFontDrawSize(FontSizeEnum fontSizeEnum) {
        return BaseConstants.imageFontDrawSizeMap.get(fontSizeEnum);
    }
}
