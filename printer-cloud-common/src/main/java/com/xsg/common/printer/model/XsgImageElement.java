package com.xsg.common.printer.model;

import lombok.Data;

/**
 * @author 高总辉
 * @desc
 * @date 2020/11/24 11:19 上午
 */
@Data
public class XsgImageElement extends XsgElement {

    private int width;

    private int height;

    private boolean isCompress;
}
