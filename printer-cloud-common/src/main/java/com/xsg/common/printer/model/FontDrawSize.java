package com.xsg.common.printer.model;

import com.xsg.common.printer.constants.FontSizeEnum;
import com.xsg.common.printer.utils.DrawUtil;
import lombok.Data;
import org.apache.commons.lang.StringUtils;

/**
 * @author 高总辉
 * @desc 字体大小
 * @date 2020/12/17 9:21 上午
 */
@Data
public class FontDrawSize {

    private FontSizeEnum fontSizeEnum;

    private int englishWidth;

    private int chineseWidth;

    private int englishHeight;

    private int chineseHeight;

    private int numberSize;

    private int specialSize;

    private String fontNameOrSize;

    public FontDrawSize(FontSizeEnum fontSizeEnum, int englishWidth, int chineseWidth, int englishHeight, int chineseHeight, int numberSize, int specialSize, String fontNameOrSize) {
        this.fontSizeEnum = fontSizeEnum;
        this.englishWidth = englishWidth;
        this.chineseWidth = chineseWidth;
        this.englishHeight = englishHeight;
        this.chineseHeight = chineseHeight;
        this.numberSize = numberSize;
        this.specialSize = specialSize;
        this.fontNameOrSize = fontNameOrSize;
    }

    public static FontDrawSize of (FontSizeEnum fontSizeEnum, int englishWidth, int chineseWidth, int englishHeight, int chineseHeight, int numberSize, int specialSize, String fontName) {
        return new FontDrawSize(fontSizeEnum, englishWidth, chineseWidth, englishHeight, chineseHeight, numberSize, specialSize, fontName);
    }

    /**
     * 获取某个字符串的总长度
     * @param str
     * @return
     */
    public int getTotalWidth (String str) {
        if (StringUtils.isBlank(str)) {
            return 0;
        }
        int width = 0;
        for(int i=0;i < str.length();i++) {
            char ch = str.charAt(i);
            if((ch >= 'a' && ch<='z') || (ch>='A' && ch<='Z')){
                width = width + englishWidth;
            } else if (ch >= '0' && ch<='9' || DrawUtil.isSpecialChar(ch)) {
                width = width + numberSize*9/10;
            } else{
                width = width + chineseWidth;
            }
        }
        return width;
    }
}
