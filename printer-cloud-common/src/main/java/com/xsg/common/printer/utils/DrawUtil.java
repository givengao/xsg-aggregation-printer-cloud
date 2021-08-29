package com.xsg.common.printer.utils;

import com.alibaba.fastjson.JSON;
import com.xsg.common.printer.model.FeedLine;
import org.apache.commons.lang.StringUtils;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 高总辉
 * @desc
 * @date 2020/12/2 2:17 下午
 */
public class DrawUtil {

    /**
     * 换行
     * @param height 行高度
     * @param width 行宽度
     * @param str 显示字符
     * @param cHeight 中文单位高度
     * @param eHeight 英文单位高度
     * @param cWidth 中文单位宽度
     * @param eWidth 英文单位宽度
     * @return
     */
    public static List<FeedLine> feedLine (int height, int width, String str, int cHeight, int eHeight, int cWidth, int eWidth) {
        if (StringUtils.isBlank(str)) {
            return new ArrayList<>();
        }
        int engishCount = 0;
        int chineseCount = 0;
        List<FeedLine> feedLines = new ArrayList<>();
        List<Character> spiltChars = new ArrayList<>();
        int splitWidth = 0;
        int splitHeight = 0;
        for(int i=0;i < str.length();i++) {
            char ch = str.charAt(i);
            if (splitHeight < height) {
                int fitWidth;
                if((ch >= 'a' && ch<='z') || (ch>='A' && ch<='Z')){
                    engishCount++;
                    fitWidth = eWidth;
                } else if (ch >= '0' && ch<='9') {
                    engishCount++;
                    fitWidth = eWidth*9/10;
                } else if (isSpecialChar(ch)) {
                    engishCount++;
                    fitWidth = eWidth*1/2;
                } else {
                    chineseCount++;
                    fitWidth = cWidth;
                }
                if (splitWidth + fitWidth <= width) {
                    spiltChars.add(ch);
                    splitWidth = splitWidth + fitWidth;
                } else {
                    FeedLine feedLine = new FeedLine();
                    feedLine.setLine(spiltChars.stream().map(e -> e.toString()).collect(Collectors.joining("")));
                    int longHeight = getHeight(chineseCount, engishCount, cHeight, eHeight);
                    splitHeight = splitHeight + longHeight;
                    feedLine.setHeight(longHeight);
                    feedLines.add(feedLine);
                    spiltChars.clear();
                    spiltChars.add(ch);
                    splitWidth = fitWidth;
                    engishCount = 1;
                    chineseCount = 1;
                }
            }
        }
        if (splitHeight < height) {
            if (!spiltChars.isEmpty()) {
                FeedLine feedLine = new FeedLine();
                feedLine.setLine(spiltChars.stream().map(e -> e.toString()).collect(Collectors.joining("")));
                int longHeight = getHeight(chineseCount, engishCount, cHeight, eHeight);
                feedLine.setHeight(longHeight);
                feedLines.add(feedLine);
            }
        }
        return feedLines;
    }

    /**
     * 图片文字换行
     * @param str
     * @param fontMetrics
     * @return
     */
    public static List<String> feedImageLine (int height, int width, String str, FontMetrics fontMetrics) {
        if (StringUtils.isBlank(str) || fontMetrics == null) {
            return new ArrayList<>();
        }
        char[] chars = str.toCharArray();
        List<String> feedLines = new ArrayList<>();
        List<Character> spiltChars = new ArrayList<>();
        int charHeight = fontMetrics.getHeight();
        int splitWidth = 0;
        int splitHeight = 0;
        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];
            int charWidth = fontMetrics.charWidth(ch);
            if (splitHeight < height) {
                if (splitWidth + charWidth <= width) {
                    spiltChars.add(ch);
                    splitWidth = splitWidth + charWidth;
                } else {
                    feedLines.add(spiltChars.stream().map(e -> e.toString()).collect(Collectors.joining("")));
                    spiltChars.clear();
                    spiltChars.add(ch);
                    splitHeight = splitHeight + charHeight;
                    splitWidth = charWidth;
                }
            }
        }
        if (splitHeight < height) {
            if (!spiltChars.isEmpty()) {
                feedLines.add(spiltChars.stream().map(e -> e.toString()).collect(Collectors.joining("")));
            }
        }
        return feedLines;
    }

    /**
     * 取最长字符长度
     * @param chineseCount
     * @param engishCount
     * @param cHeight
     * @param eHeight
     * @return
     */
    private static int getHeight (int chineseCount, int engishCount, int cHeight, int eHeight) {
        int height = 0;
        if (engishCount > 0 && chineseCount == 0) {
            height = eHeight;
        } else if (engishCount == 0 && chineseCount > 0) {
            height = cHeight;
        } else if (engishCount > 0 && chineseCount > 0){
            if (cHeight > eHeight) {
                height = cHeight;
            } else {
                height = eHeight;
            }
        } else {
            height = cHeight;
        }
        return height;
    }

    public static boolean isSpecialChar (char ch) {
        String cha = String.valueOf(ch);
        String regEx = "[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}「」【】‘；：”“’。，、？]|\n|\r|\t";
        return cha.matches(regEx);
    }

    public static String getSpecialStr (String path) {
        if (path == null) {
            return null;
        }
        if (path.contains("收")) {
            return "收";
        }
        if (path.contains("寄")) {
            return "寄";
        }
        if (path.contains("T")) {
            return "T";
        }
        return null;
    }


    public static void main (String args[]) {
        List<FeedLine> sp = feedLine(20, 100, "qwqwertyui哈哈哈哈哈哈哈哈哈哈呵呵呵呵啊啊啊啊啊啊啊", 10, 9, 10, 5);
        System.out.println(JSON.toJSONString(sp));
    }
}
