package com.xsg.common.printer.model;

import lombok.Data;

/**
 * @author 高总辉
 * @desc 显示元素扩展，便于存储元素所有属性信息 只有其中一个不为空
 * @date 2020/12/11 2:41 下午
 */
@Data
public class XsgElementAll {

    /**
     * 图片显示元素
     */
    private XsgImageElement imageElement;

    /**
     * 文字显示元素
     */
    private XsgFontElement fontElement;

    /**
     * 二维码显示元素
     */
    private XsgQrCodeElement qrCodeElement;

    /**
     * 条形码显示元素
     */
    private XsgBarcodeElement barcodeElement;
}
