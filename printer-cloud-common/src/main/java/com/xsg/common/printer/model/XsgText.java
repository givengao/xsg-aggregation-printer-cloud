package com.xsg.common.printer.model;

import lombok.Data;

/**
 * @author 高总辉
 * @desc 文字
 * @date 2020/11/23 10:23 上午
 */
@Data
public class XsgText {

    /**
     * 文字大小
     */
    private int size;

    /**
     * 文字类型
     */
    private String type;

    /**
     * 文字风格
     */
    private String style;
}
