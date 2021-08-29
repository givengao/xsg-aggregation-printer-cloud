package com.xsg.common.printer.model;

import lombok.Data;

/**
 * @author 高总辉
 * @desc 最小显示元素
 * @date 2020/11/23 11:36 上午
 */
@Data
public class XsgElement {

    /**
     * 类型 1:文字 2:图片 3:二维码 4:条形码
     */
    private int type;

    /**
     * 显示的属性信息
     */
    private XsgFiled filed;

    /**
     * 元素显示间距
     */
    private XsgMargin margin;

    /**
     * 左上角起始坐标
     */
    private XsgCoordinate startCoordinate;

    /**
     * 右下角终止坐标
     */
    private XsgCoordinate endCoordinate;
}
