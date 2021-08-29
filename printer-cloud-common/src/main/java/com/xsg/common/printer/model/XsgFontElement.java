package com.xsg.common.printer.model;

import com.xsg.common.printer.constants.FontSizeEnum;
import lombok.Data;

/**
 * @author 高总辉
 * @desc 文字显示元素
 * @date 2020/11/23 11:50 上午
 */
@Data
public class XsgFontElement extends XsgElement {

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
}
