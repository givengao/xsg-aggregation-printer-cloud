package com.xsg.common.printer.core;

import com.alibaba.fastjson.JSON;
import com.xsg.common.printer.constants.TemplateConstants;
import com.xsg.common.printer.covert.ElementCovert;
import com.xsg.common.printer.model.XsgTemplate;
import com.xsg.common.printer.vo.WaybillVo;

/**
 * @author 高总辉
 * @desc
 * @date 2020/12/21 7:31 下午
 */
public class InApp {

    public static void main (String [] args) {
        WaybillVo waybillVo = getDefaultVo();
//        toPrint(TemplateConstants.getQsTemplate());
//        toPrint(TemplateConstants.getPkgTemplate());
//        toPrint(TemplateConstants.getTuHuoPkgTemplate());
        toPrint(TemplateConstants.getTuHuoQsTemplate());
//        System.out.println(VoucherHandler.print(TemplateConstants.getPkgTemplate(), VoucherEnum.INSTRUCT_TSPL_HPRT, waybillVo));
    }

    private static void toPrint (XsgTemplate template) {
        System.out.println(JSON.toJSONString(ElementCovert.toAll(template), true));
    }

    private static WaybillVo getDefaultVo () {
        WaybillVo waybillVo = new WaybillVo();
        waybillVo.setServiceTel("");
        waybillVo.setEndStationName("");
        waybillVo.setStreetName("");
        waybillVo.setReceiptCustomer("");
        waybillVo.setReceiptPhone("");
        waybillVo.setReceiptAddress("");
        waybillVo.setShipperCustomer("");
        waybillVo.setShipperPhone("");
        waybillVo.setShipperAddress("");
        waybillVo.setQty("");
        waybillVo.setConfirmCode("");
        waybillVo.setStartStationName("");
        waybillVo.setWaybillId("BC2101144500600");
        waybillVo.setPackageNo("BC2101144500600-01");
        waybillVo.setCollFee("");
        waybillVo.setCurrentPackageNum("");
        waybillVo.setPackageDetail("");
        waybillVo.setRemark("");
        waybillVo.setPayType("");
        waybillVo.setPrintTime("");
        waybillVo.setPrintBy("");
        return waybillVo;
    }
}
