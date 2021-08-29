package com.xsg.common.printer.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 高总辉
 * @desc
 * @date 2020/12/17 4:54 下午
 */
@Data
public class WaybillVo {

    @ApiModelProperty(value = "客服电话")
    private String serviceTel;

    @ApiModelProperty(value = "目的地站点名")
    private String endStationName;

    @ApiModelProperty(value = "街道地址")
    private String streetName;

    @ApiModelProperty(name = "收货客户名", value = "收货客户名.例:XX店")
    private String receiptCustomer;

    @ApiModelProperty(name = "收货方联系电话", value = "收货方联系电话.例:13699996666")
    private String receiptPhone;

    @ApiModelProperty(name = "收货地址", value = "收货地址.例:广东省深圳市罗湖区红岗路1102号")
    private String receiptAddress;

    @ApiModelProperty(name = "发货客户名", value = "发货客户名.例:XX店")
    private String shipperCustomer;

    @ApiModelProperty(name = "发货人联系电话", value = "发货人联系电话.例:13699996666")
    private String shipperPhone;

    @ApiModelProperty(name = "发货地址", value = "发货地址.例:广东省深圳市罗湖区红岗路1102号")
    private String shipperAddress;

    @ApiModelProperty(name = "货物件数", value = "货物件数.例:1件")
    private String qty;

    @ApiModelProperty(value = "回单码")
    private String confirmCode;

    @ApiModelProperty(value = "起始站点名")
    private String startStationName;

    @ApiModelProperty(name = "订单编号", value = "订单编号.例:BX1447885")
    private String waybillId;

    @ApiModelProperty(name = "包裹号", value = "包裹号.例:BX1447885-01")
    private String packageNo;

    @ApiModelProperty(name = "代收金额", value = "代收金额.例:0.如果大于0代表是代收货款")
    private String collFee;

    @ApiModelProperty(name = "当前包裹件数/运单总件数", value = "3/5")
    private String currentPackageNum;

    @ApiModelProperty(name = "包裹明细", value = "3大2小")
    private String packageDetail;

    @ApiModelProperty(value = "备注信息")
    private String remark;

    @ApiModelProperty(name = "支付方式", value = "支付方式.[balance-余额支付;weixin-微信支付;wb-微信/余额混合支付]")
    private String payType;

    @ApiModelProperty(value = "打印时间")
    private String printTime;

    @ApiModelProperty(value = "打印人")
    private String printBy;
}
