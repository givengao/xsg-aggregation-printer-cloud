package com.xsg.common.printer.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author 高总辉
 * @desc 边框样式
 * @date 2020/11/23 10:53 上午
 */
@Data
@Accessors(chain = true)
public class XsgBorder {

    /**
     * 顶部线条类型 1:实线 2:虚线
     */
    private int topStyle;

    /**
     * 右部线条类型 1:实线 2:虚线
     */
    private int rightStyle;

    /**
     * 左部线条类型 1:实线 2:虚线
     */
    private int leftStyle;

    /**
     * 下部线条类型 1:实线 2:虚线
     */
    private int bottomStyle;

    /**
     * 背景颜色 1:白色 2:黑色 3:黄色
     */
    private int color;
}
