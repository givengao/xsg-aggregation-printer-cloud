//package com.xsg.common.printer.utils;
//
//import cn.hutool.core.util.StrUtil;
//import com.xiaoma.mesh.common.core.enums.ResultCodeEnum;
//import com.xiaoma.mesh.common.core.exception.BusinessException;
//import com.xiaoma.mesh.common.utils.SpringContextUtil;
//import com.xsg.common.printer.constants.DeviceTypeEnum;
//import com.xiaoma.voucher.service.third.api.*;
//
//public class CloudPlatformUtil {
//    public static CloudApi getCloudApiImpl(DeviceTypeEnum deviceTypeEnum){
//        CloudApi cloudApi = null;
//        switch (deviceTypeEnum){
//            case BLUETOOTH:
//            case CLOUD:
//                cloudApi = SpringContextUtil.getBean(HrptCloudApi.class);
//                break;
//            case KUAIDI100:
//                cloudApi = SpringContextUtil.getBean(KuaiDi100CloudApi.class);
//                break;
//            case FHD001:
//                cloudApi = SpringContextUtil.getBean(FHD001CloudApi.class);
//                break;
//            case NATIVE_CLOUD:
//                cloudApi = SpringContextUtil.getBean(NativeCloudApi.class);
//                break;
//            default:
//                throw new BusinessException(ResultCodeEnum.FAIL.getCode(), StrUtil.format("无实现的设备类型：{}", deviceTypeEnum.getName()));
//        }
//        return cloudApi;
//    }
//}
