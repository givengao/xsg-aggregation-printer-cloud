package com.xsg.common.printer.model;

import lombok.Data;

import java.util.List;

/**
 * @author 高总辉
 * @desc 模版
 * @date 2020/11/23 10:39 上午
 */
@Data
public class XsgTemplate {

    /**
     * 纸张类型：1:A4
     */
    private int paperType;

    /**
     * 宽度
     */
    private int width;

    /**
     * 高度
     */
    private int height;

    /**
     * 背景颜色 1:白色 2:黑色 3:黄色
     */
    private int color;

    /**
     * 边框样式
     */
    private XsgBorder border;

    /**
     * 行
     */
    private List<XsgRow> rows;
}
