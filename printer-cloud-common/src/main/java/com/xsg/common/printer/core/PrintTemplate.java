package com.xsg.common.printer.core;

import com.xsg.common.printer.constants.FontSizeEnum;
import com.xsg.common.printer.model.*;
import com.xsg.common.printer.vo.TemplateVo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 高总辉
 * @desc
 * @date 2020/11/24 3:56 下午
 */
public class PrintTemplate {

    /**
     * 获取跨城打印模版
     * @return
     */
    public static XsgTemplate getKcXsgTemplate () {
        XsgTemplate xsgTemplate = new XsgTemplate();
        xsgTemplate.setPaperType(1);
        xsgTemplate.setColor(1);
        xsgTemplate.setBorder(getDefaultBorder());
        xsgTemplate.setWidth(505);
        xsgTemplate.setHeight(405);

        List<XsgRow> rows = new ArrayList<>();
        //第1行
        XsgRow row1 = getDefaultRow(80);
        List<XsgCell> row1_cells = new ArrayList<>();

        XsgCell row1_cell3 = getDefaultCell(100, true);
        List<XsgElement> row1_cell3_elements = new ArrayList<>();
        row1_cell3_elements.add(getDefaultElement2());
        row1_cell3.setElements(row1_cell3_elements);
        row1_cells.add(row1_cell3);


        XsgCell row1_cell1 = getDefaultCell(300, true);
        List<XsgElement> row1_cell1_elements = new ArrayList<>();
//        row1_cell1_elements.add(getDefaultElement4());
        row1_cell1.setElements(row1_cell1_elements);
        row1_cells.add(row1_cell1);

        XsgCell row1_cell2 = getDefaultCell(105, true);
        List<XsgElement> row1_cell2_elements = new ArrayList<>();
        row1_cell2_elements.add(getDefaultElement3());
        row1_cell2.setElements(row1_cell2_elements);
        row1_cells.add(row1_cell2);

        row1.setCells(row1_cells);


        //第2行
        XsgRow row2 = getDefaultRow(200);
        //第2行 - 第1列
        List<XsgCell> cell2s = new ArrayList<>();

        XsgCell cell2_1 = getDefaultCell(337, false);
        List<XsgRow> cell2_1_rows = new ArrayList<>();
        XsgRow cell2_1_row1 = getDefaultRow(67);
        List<XsgCell> cell2_1_row1_cells = new ArrayList<>();
        XsgCell cell2_1_row1_cell1 = getDefaultCell(100, true);
        List<XsgElement> cell2_1_row1_cell1_elements = new ArrayList<>();
        XsgElement cell2_1_row1_cell1_element1 = getDefaultElement1();
        cell2_1_row1_cell1_elements.add(cell2_1_row1_cell1_element1);
        cell2_1_row1_cell1.setElements(cell2_1_row1_cell1_elements);
        cell2_1_row1_cells.add(cell2_1_row1_cell1);
        cell2_1_row1.setCells(cell2_1_row1_cells);

        XsgRow cell2_1_row2 = getDefaultRow(67);
        List<XsgCell> cell2_1_row2_cells = new ArrayList<>();
        XsgCell cell2_1_row2_cell1 = getDefaultXuCell(37);
        XsgCell cell2_1_row2_cell2 = getDefaultCell(300, false);
        cell2_1_row2_cells.add(cell2_1_row2_cell1);
        cell2_1_row2_cells.add(cell2_1_row2_cell2);
        cell2_1_row2.setCells(cell2_1_row2_cells);

        XsgRow cell2_1_row3 = getDefaultRow(66);
        List<XsgCell> cell2_1_row3_cells = new ArrayList<>();
        XsgCell cell2_1_row3_cell1 = getDefaultXuCell(37);
        XsgCell cell2_1_row3_cell2 = getDefaultCell(300, false);
        cell2_1_row3_cells.add(cell2_1_row3_cell1);
        cell2_1_row3_cells.add(cell2_1_row3_cell2);
        cell2_1_row3.setCells(cell2_1_row3_cells);

        cell2_1_rows.add(cell2_1_row1);
        cell2_1_rows.add(cell2_1_row2);
        cell2_1_rows.add(cell2_1_row3);
        cell2_1.setRows(cell2_1_rows);

        cell2s.add(cell2_1);
        //第2行 - 第2列
        XsgCell cell2_2 = getDefaultCell(167, false);
        List<XsgRow> cell2_2_rows = new ArrayList<>();
        cell2_2_rows.add(getDefaultXuRow(53));
        cell2_2_rows.add(getDefaultXuRow(52));
        cell2_2_rows.add(getDefaultXuRow(52));
        cell2_2_rows.add(getDefaultRow(43));
        cell2_2.setRows(cell2_2_rows);
        cell2s.add(cell2_2);

        row2.setCells(cell2s);

        //第3行
        XsgRow row3 = getDefaultRow(52);

        //第4行
        XsgRow row4 = getDefaultRow(72);
        List<XsgCell> row4_cells = new ArrayList<>();

        XsgCell row4_cell1 = getDefaultCell(105, true);
        List<XsgElement> row4_cell1_elements = new ArrayList<>();
        row4_cell1_elements.add(getDefaultElement3());
        row4_cell1.setElements(row4_cell1_elements);
        row4_cells.add(row4_cell1);

        XsgCell row4_cell2 = getDefaultCell(400, true);
        List<XsgElement> row4_cell2_elements = new ArrayList<>();
//        row4_cell2_elements.add(getDefaultElement4());
        row4_cell2.setElements(row4_cell2_elements);
        row4_cells.add(row4_cell2);

        row4.setCells(row4_cells);

        rows.add(row1);
        rows.add(row2);
        rows.add(row3);
        rows.add(row4);
        xsgTemplate.setRows(rows);
        return xsgTemplate;
    }

    private static XsgBorder getDefaultBorder () {
        XsgBorder border = new XsgBorder();
        border.setTopStyle(1);
        border.setRightStyle(1);
        border.setLeftStyle(1);
        border.setBottomStyle(1);
        border.setColor(2);
        return border;
    }

    public static XsgRow getDefaultRow (int height) {
        XsgRow xsgRow = new XsgRow();
        xsgRow.setHeight(height);
        xsgRow.setBorder(getDefaultBorder());
        xsgRow.setCells(null);
        return xsgRow;
    }

    public static XsgRow getDefaultRow (int height, boolean hidden) {
        XsgRow xsgRow = new XsgRow();
        xsgRow.setHeight(height);
        xsgRow.setBorder(getDefaultBorder());
        xsgRow.setCells(null);
        if (hidden) {
            XsgBorder border = xsgRow.getBorder();
            border.setTopStyle(0);
            border.setRightStyle(0);
            border.setLeftStyle(0);
            border.setBottomStyle(0);
        }
        return xsgRow;
    }

    private static XsgRow getDefaultXuRow (int height) {
        XsgRow xsgRow = new XsgRow();
        xsgRow.setHeight(height);
        xsgRow.setBorder(getDefaultBorder());
        xsgRow.setCells(null);
        XsgBorder xsgBorder = xsgRow.getBorder();
        xsgBorder.setTopStyle(2);
        xsgBorder.setRightStyle(2);
        xsgBorder.setLeftStyle(2);
        xsgBorder.setBottomStyle(2);
        xsgBorder.setColor(2);
        return xsgRow;
    }

    public static XsgCell getDefaultCell (int width, boolean hidden) {
        XsgCell xsgCell = new XsgCell();
        xsgCell.setWidth(width);
        xsgCell.setBorder(getDefaultBorder());
        xsgCell.setRows(null);
        xsgCell.setElements(null);
        if (hidden) {
            XsgBorder border = xsgCell.getBorder();
            border.setTopStyle(0);
            border.setRightStyle(0);
            border.setLeftStyle(0);
            border.setBottomStyle(0);
        }
        return xsgCell;
    }

    /**
     * 虚线cell
     * @param width
     * @return
     */
    private static XsgCell getDefaultXuCell (int width) {
        XsgCell xsgCell = new XsgCell();
        xsgCell.setWidth(width);
        xsgCell.setBorder(getDefaultBorder());
        xsgCell.setRows(null);
        xsgCell.setElements(null);
        XsgBorder xsgBorder = xsgCell.getBorder();
        xsgBorder.setTopStyle(2);
        xsgBorder.setRightStyle(2);
        xsgBorder.setLeftStyle(2);
        xsgBorder.setBottomStyle(2);
        xsgBorder.setColor(2);
        return xsgCell;
    }

    private static XsgElement getDefaultElement1 () {
        XsgFontElement xsgElement = new XsgFontElement();
        xsgElement.setType(1);
        xsgElement.setFiled(new XsgFiled().setTitle("汽运").setValue("(13:15)"));
        xsgElement.setMargin(new XsgMargin().setTop(5).setBottom(10).setLeft(10).setRight(10));
        xsgElement.setFontSize(20);
        return xsgElement;
    }

    private static XsgElement getDefaultElement2 () {
        XsgImageElement xsgElement = new XsgImageElement();
        xsgElement.setType(2);
        xsgElement.setFiled(new XsgFiled().setTitle("图片").setValue("/Users/zonghuigao/Desktop/image/xiaoma.png"));
        xsgElement.setMargin(new XsgMargin().setTop(10).setBottom(10).setLeft(10).setRight(10));
        xsgElement.setHeight(80);
        xsgElement.setWidth(80);
        return xsgElement;
    }

    private static XsgElement getDefaultElement3 () {
        XsgQrCodeElement xsgElement = new XsgQrCodeElement();
        xsgElement.setType(3);
        xsgElement.setFiled(new XsgFiled().setTitle("二维码").setValue("HSAA201123009374"));
        xsgElement.setMargin(new XsgMargin().setTop(10).setBottom(10).setLeft(10).setRight(10));
        xsgElement.setHeight(100);
        xsgElement.setWidth(100);
        return xsgElement;
    }

    private static XsgElement getDefaultElement4 () {
        XsgBarcodeElement xsgElement = new XsgBarcodeElement();
        xsgElement.setType(4);
        xsgElement.setFiled(new XsgFiled().setTitle("条形码").setValue("HSAA201123009374"));
        xsgElement.setMargin(new XsgMargin().setTop(10).setBottom(10).setLeft(30).setRight(10));
        xsgElement.setHeight(70);
        xsgElement.setWidth(300);
        return xsgElement;
    }

    public static XsgFiled getDefaultField (String value) {
        return new XsgFiled().setTitle("title").setValue(value);
    }

    public static XsgMargin getDefaultMargin () {
        return new XsgMargin().setTop(0).setBottom(0).setLeft(0).setRight(0);
    }

    public static XsgTemplate getTCTemplate () {
        TemplateFluent fluent = TemplateFluent.template();

        fluent.start(100*8, 70*8, 1, false)
                .row(30*8, TemplateFluent.Cell.create()
                        .cell(30*8, TemplateFluent.Element.create()
                                .textElement(getDefaultField("总辉"), getDefaultMargin(), FontSizeEnum.MIDDLE)
                        )
                        .cell(40*8, TemplateFluent.Element.create()
                                .barCodeElement(getDefaultField("HSAA201123009374"), getDefaultMargin(), 30*8, 40*8)
                        )
                ).row(30*8, TemplateFluent.Cell.create()
                        .cell(30*8, TemplateFluent.Element.create()
                                .qrCodeElement(getDefaultField("HSAA201123009374"), getDefaultMargin(), 30*8, 40*8)
                        )
                        .cell(40*8, TemplateFluent.Element.create()
                                .barCodeElement(getDefaultField("HSAA201123009374"), getDefaultMargin(), 30*8, 40*8)
                        )
                ).row(40*8, TemplateFluent.Cell.create()
                        .cell(30*8, TemplateFluent.Element.create()
                                .qrCodeElement(getDefaultField("HSAA201123009374"), getDefaultMargin(), 40*8, 40*8)
                        )
                        .cell(40*8, TemplateFluent.Element.create()
                                .qrCodeElement(getDefaultField("HSAA201123009374"), getDefaultMargin(), 40*8, 40*8)
                        )
                );
        return fluent.end();
    }

    public static XsgTemplate getTclTemplate () {
        TemplateFluent fluent = TemplateFluent.template();

        fluent.start(100*8, 70*8, 1, false)
                .row(10*8, TemplateFluent.Cell.create()
                        .cellH(28*8, TemplateFluent.Element.create()
                                .imageElement(new XsgFiled<TemplateVo>().setValueFunction(TemplateVo::getCname).setValue("/Users/zonghuigao/Desktop/image/sz_logo.png"), getDefaultMargin().setLeft(17*8).setTop(3), 10*8, 16*8)
                        )
                        .cellH(42*8, TemplateFluent.Element.create()
                                .texBElement(new XsgFiled<TemplateVo>().setValueFunction(TemplateVo::getCname).setName("cname").setValue("小高快送"), getDefaultMargin().setLeft(3).setTop(0), FontSizeEnum.BIG)
                                .textElement(new XsgFiled<TemplateVo>().setTitle("客服：").setValueFunction(TemplateVo::getTel).setValue("15613874848"), getDefaultMargin().setLeft(3).setTop(50), FontSizeEnum.MIDDLE)
                        )
                ).row(10*8, TemplateFluent.Cell.create()
                        .cellH(70*8, TemplateFluent.Element.create()
                                .textElement(new XsgFiled<TemplateVo>().setValueFunction(TemplateVo::getStreetName).setValue("解放-C东关街道"), getDefaultMargin().setLeft(10).setTop(5), FontSizeEnum.BIG)
                        )
                ).rowX(25*8, TemplateFluent.Cell.create()
                        .cellH(15*8, TemplateFluent.Element.create()
                             .textElement(new XsgFiled<TemplateVo>().setValueFunction(TemplateVo::getCname).setValue("收"), getDefaultMargin().setLeft(10).setTop(5), FontSizeEnum.BIG)
                        ).cellH(55*8, TemplateFluent.Row.create()
                             .rowH(5*8, TemplateFluent.Cell.create()
                                     .cellH(55*8, TemplateFluent.Element.create()
                                             .textElement(new XsgFiled<TemplateVo>().setValueFunction(TemplateVo::getSenderName).setValue("解放广场旁边--收货方"),  getDefaultMargin().setLeft(10).setTop(2),FontSizeEnum.MIDDLE)
                                     )
                             ).rowH(5*8, TemplateFluent.Cell.create()
                                     .cellH(55*8, TemplateFluent.Element.create()
                                             .textElement(new XsgFiled<TemplateVo>().setValueFunction(TemplateVo::getSenderContactPhone).setValue("09271604"),  getDefaultMargin().setLeft(10).setTop(2),FontSizeEnum.MIDDLE)
                                     )
                             ).rowH(15*8, TemplateFluent.Cell.create()
                                     .cellH(55*8, TemplateFluent.Element.create()
                                             .textElement(new XsgFiled<TemplateVo>().setValueFunction(TemplateVo::getSenderContactPhone).setValue("河南省南阳市王成路123接到212"),  getDefaultMargin().setLeft(10).setTop(2),FontSizeEnum.MIDDLE)
                                     )
                             )
                        )
                ).row(20*8, TemplateFluent.Cell.create()
                        .cellH(15*8, TemplateFluent.Element.create()
                                .textElement(new XsgFiled<TemplateVo>().setValueFunction(TemplateVo::getCname).setValue("寄"), getDefaultMargin().setLeft(10).setTop(5), FontSizeEnum.BIG)
                        ).cellH(55*8, TemplateFluent.Row.create()
                                .rowH(5*8, TemplateFluent.Cell.create()
                                        .cellH(55*8, TemplateFluent.Element.create()
                                                .textElement(new XsgFiled<TemplateVo>().setValueFunction(TemplateVo::getAddresseeName).setValue("解放广场旁边--寄货方"),  getDefaultMargin().setLeft(10).setTop(2),FontSizeEnum.MIDDLE)
                                        )
                                ).rowH(5*8, TemplateFluent.Cell.create()
                                        .cellH(55*8, TemplateFluent.Element.create()
                                                .textElement(new XsgFiled<TemplateVo>().setValueFunction(TemplateVo::getAddresseeContactPhone).setValue("18129858525"),  getDefaultMargin().setLeft(10).setTop(2),FontSizeEnum.MIDDLE)
                                        )
                                ).rowH(10*8, TemplateFluent.Cell.create()
                                        .cellH(55*8, TemplateFluent.Element.create()
                                                .textElement(new XsgFiled<TemplateVo>().setValueFunction(TemplateVo::getAddresseeDetailAddress).setValue("河南省南阳市王成路是路极为八街sawwqwqwqqw千万千万千万千万千万千万千万千万凄凄切切凄凄切切凄凄切切凄凄切切凄凄切切凄凄切切凄凄切切"),  getDefaultMargin().setLeft(10).setRight(0).setTop(2),FontSizeEnum.SMALL)
                                        )
                                )
                        )
                ).row(25*8, TemplateFluent.Cell.create()
                        .cellH(18*8, TemplateFluent.Row.create()
                                .row(13*8, TemplateFluent.Cell.create()
                                        .cellX(18*8, TemplateFluent.Element.create()
                                                .textElement(new XsgFiled<TemplateVo>().setValueFunction(TemplateVo::getAddresseeDetailAddress).setValue("88-A营业部"),  getDefaultMargin().setLeft(10).setTop(2),FontSizeEnum.MIDDLE)
                                        )
                                ).row(12*8, TemplateFluent.Cell.create()
                                        .cellX(18*8, TemplateFluent.Element.create()
                                                .textElement(new XsgFiled<TemplateVo>().setValueFunction(TemplateVo::getQty).setValue("1件"),  getDefaultMargin().setLeft(10).setTop(2),FontSizeEnum.MIDDLE)
                                        )
                                )
                        ).cell(34*8, TemplateFluent.Element.create()
                                .qrCodeElement(new XsgFiled<TemplateVo>().setValueFunction(TemplateVo::getOrderNo).setValue("wqw1221212223223"),  getDefaultMargin().setLeft(8*8).setTop(2*8),25*8, 34*8)
                        ).cellH(18*8, TemplateFluent.Row.create()
                                .row(13*8, TemplateFluent.Cell.create()
                                        .cellH(18*8, TemplateFluent.Element.create()
                                                .textElement(new XsgFiled<TemplateVo>().setValueFunction(TemplateVo::getQty).setValue("代收货款:"),  getDefaultMargin().setLeft(2).setTop(2),FontSizeEnum.MIDDLE)
                                        )
                                ) .row(12*8, TemplateFluent.Cell.create()
                                        .cellH(18*8, TemplateFluent.Element.create()
                                                .textElement(new XsgFiled<TemplateVo>().setValueFunction(TemplateVo::getQty).setValue("收件签字:"),  getDefaultMargin().setLeft(2).setTop(2),FontSizeEnum.MIDDLE)
                                                .textElement(new XsgFiled<TemplateVo>().setValueFunction(TemplateVo::getQty).setValue("日期: 年 月 日"),  getDefaultMargin().setLeft(2).setRight(1).setTop(8*8),FontSizeEnum.SMALL)
                                        )
                                )
                        )
                ).rowH(10*8, TemplateFluent.Cell.create()
                        .cellH(70*8, TemplateFluent.Element.create()
                                .textElement(new XsgFiled<TemplateVo>().setValueFunction(TemplateVo::getQty).setValue("备注备注备注备注备注备注备注备注备注备注"),  getDefaultMargin().setLeft(2).setTop(2),FontSizeEnum.MIDDLE)
                                .textElement(new XsgFiled<TemplateVo>().setValueFunction(TemplateVo::getQty).setValue("结算：余额支付"),  getDefaultMargin().setLeft(2).setTop(8*6),FontSizeEnum.MIDDLE)
                        )
                );

        return fluent.end();
    }
}
