package com.xsg.common.printer.utils;

import com.xsg.common.printer.constants.VoucherConstants;

import java.util.UUID;

/**
 * @author 高总辉
 * @desc
 * @date 2020/12/16 10:42 上午
 */
public class PathUtil {

    public static String getPngPath () {
        return VoucherConstants.PRINT_PATH +  "/" + UUID.randomUUID().toString() + ".png";
    }

    public static String getPdfPath () {
        return VoucherConstants.PRINT_PATH + "/" + UUID.randomUUID().toString() + ".pdf";
    }

    public static String getRandomPngName () {
        return UUID.randomUUID().toString() + ".png";
    }
}
