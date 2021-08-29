package com.xsg.common.printer.model;

import lombok.Data;

import java.util.List;

/**
 * @author 高总辉
 * @desc 行
 * @date 2020/11/23 10:38 上午
 */
@Data
public class XsgRow {

    /**
     * 高度
     */
    private int height;

    /**
     * 边框类型
     */
    private XsgBorder border;

    /**
     * 表格
     */
    private List<XsgCell> cells;
}
