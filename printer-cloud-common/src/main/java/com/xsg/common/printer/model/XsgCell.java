package com.xsg.common.printer.model;

import lombok.Data;

import java.util.List;

/**
 * @author 高总辉
 * @desc 元素
 * @date 2020/11/23 11:04 上午
 */
@Data
public class XsgCell {

    /**
     * 宽度
     */
    private int width;

    /**
     * 边框样式
     */
    private XsgBorder border;

    /**
     * 列拆行
     */
    private List<XsgRow> rows;

    /**
     * 显示的元素
     */
    private List<XsgElement> elements;

    /**
     * 显示的元素,便于存储显示元素全量信息(只用于前端和存储，不参与运算逻辑)
     */
    private List<XsgElementAll> elementAlls;
}
