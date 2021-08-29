package com.xsg.common.printer.model;

import com.xsg.common.printer.constants.FontSizeEnum;
import lombok.Data;

/**
 * @author 高总辉
 * @desc
 * @date 2020/11/26 5:44 下午
 */
@Data
public class XsgFont {
    /**
     * 字体大小
     */
    private int fontSize;

    /**
     * 字体大小枚举
     */
    private FontSizeEnum fontSizeEnum;

    /**
     * 字体风格
     */
    private int fontStyle;

    /**
     * 字体名称
     */
    private int fontNameType;

    /**
     * 字体加粗
     */
    private boolean bold;

    /**
     * 字体放大
     */
    private int mag;

    /**
     * 是否换行
     */
    private boolean needFeedLine;

    /**
     * 是否自适应宽度
     */
    private boolean fit;
}
