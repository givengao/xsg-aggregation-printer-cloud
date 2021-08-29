package com.xsg.common.printer.model;

import lombok.Data;

/**
 * @author 高总辉
 * @desc 二维码
 * @date 2020/11/24 2:27 下午
 */
@Data
public class XsgQrCodeElement extends XsgElement {

    private int width;

    private int height;
}
