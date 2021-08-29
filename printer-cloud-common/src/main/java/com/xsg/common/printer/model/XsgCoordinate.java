package com.xsg.common.printer.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author 高总辉
 * @desc 坐标
 * @date 2020/11/23 10:17 上午
 */
@Data
@Accessors(chain = true)
public class XsgCoordinate {

    private int x;

    private int y;
}
