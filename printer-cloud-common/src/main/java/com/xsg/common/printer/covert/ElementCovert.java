package com.xsg.common.printer.covert;


import com.xsg.common.printer.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 高总辉
 * @desc
 * @date 2020/12/11 3:40 下午
 */
public class ElementCovert {

    /**
     * 显示元素转换
     * @param template
     * @return
     */
    public static XsgTemplate toAll (XsgTemplate template) {
        List<XsgRow> rows = template.getRows();
        if (rows == null || rows.isEmpty()) {
            return template;
        }
        toAll(rows);
        return template;
    }

    /**
     * 显示元素转换
     * @param rows
     */
    public static void toAll (List<XsgRow> rows) {
        if (rows == null || rows.isEmpty()) {
            return;
        }
        //行
        for (XsgRow row : rows) {
            List<XsgCell> cells = row.getCells();
            if (cells != null && !cells.isEmpty()) {
                for (XsgCell cell : cells) {
                    //填充显示数据
                    List<XsgElement> elements = cell.getElements();
                    List<XsgElementAll> elementAlls = new ArrayList<>();
                    List<XsgRow> cellRows = cell.getRows();
                    if (elements != null && !elements.isEmpty()) {
                        for (XsgElement element : elements) {
                            XsgElementAll xsgElementAll = new XsgElementAll();
                            if (element instanceof XsgFontElement) {
                                xsgElementAll.setFontElement((XsgFontElement)element);
                            } else if (element instanceof XsgImageElement) {
                                xsgElementAll.setImageElement((XsgImageElement)element);
                            } else if (element instanceof XsgBarcodeElement) {
                                xsgElementAll.setBarcodeElement((XsgBarcodeElement)element);
                            } else if (element instanceof XsgQrCodeElement) {
                                xsgElementAll.setQrCodeElement((XsgQrCodeElement)element);
                            }
                            elementAlls.add(xsgElementAll);
                        }
                        cell.setElements(null);
                        cell.setElementAlls(elementAlls);
                    }
                    if (cellRows != null && !cellRows.isEmpty()) {
                        toAll(cellRows);
                    }
                }
            }
        }
    }

    /**
     * 显示元素转换
     * @param template
     */
    public static XsgTemplate toCal (XsgTemplate template) {
        List<XsgRow> rows = template.getRows();
        toCal(rows);
        return template;
    }

    /**
     * 显示元素转换
     * @param rows
     */
    public static void toCal (List<XsgRow> rows) {
        if (rows == null || rows.isEmpty()) {
            return ;
        }
        //行
        for (XsgRow row : rows) {
            List<XsgCell> cells = row.getCells();
            if (cells != null && !cells.isEmpty()) {
                for (XsgCell cell : cells) {
                    //填充显示数据
                    List<XsgElement> elements = new ArrayList<>();
                    List<XsgElementAll> elementAlls = cell.getElementAlls();
                    List<XsgRow> cellRows = cell.getRows();
                    if (elementAlls != null && !elementAlls.isEmpty()) {
                        for (XsgElementAll elementAll : elementAlls) {
                            elements.add(getOneElement(elementAll));
                        }
                        cell.setElements(elements);
                        cell.setElementAlls(null);
                    }
                    if (cellRows != null && !cellRows.isEmpty()) {
                        toCal(cellRows);
                    }
                }
            }
        }
    }

    /**
     * 取其中不为空的显示元素
     * @param elementAll
     * @return
     */
    private static XsgElement getOneElement (XsgElementAll elementAll) {
        if (elementAll == null) {
            return null;
        }
        if (elementAll.getFontElement() != null) {
            return elementAll.getFontElement();
        }
        if (elementAll.getBarcodeElement() != null) {
            return elementAll.getBarcodeElement();
        }
        if (elementAll.getQrCodeElement() != null) {
            return elementAll.getQrCodeElement();
        }
        if (elementAll.getImageElement() != null) {
            return elementAll.getImageElement();
        }
        return null;
    }
}
