package com.xsg.common.printer.action;

import com.xsg.common.printer.action.process.ElementProcess;
import com.xsg.common.printer.constants.VoucherEnum;
import com.xsg.common.printer.model.*;
import com.xsg.common.printer.utils.MapUtil;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * @author 高总辉
 * @desc
 * @date 2020/11/26 6:04 下午
 */
public class VoucherHandler {

    /**
     * 打印
     * @param template
     * @param param
     * @return
     */
    public static String print (XsgTemplate template, VoucherEnum voucherEnum, Object param) {
        if (template == null) {
            return null;
        }
        if (template.getHeight() == 0 || template.getWidth() == 0) {
            return null;
        }
        //将主数据填充至模版显示元素当中
        setElementValue(template, param);
        //创建画板
        VoucherBoard voucherBoard = VoucherBoardFactory.createBoard(template.getHeight(), template.getWidth(), template.getColor(), voucherEnum);
        if (voucherBoard == null) {
            return null;
        }
        BoardPaint boardPaint = voucherBoard.createBoard();
        //设置面单四边的长高
        setTemplateLine(boardPaint, template);
        //设置行信息
        setRowConfig(boardPaint, 0, 0, template.getWidth(), template.getRows());

        return voucherBoard.getResult();
    }

    /**
     * 设置整体模版
     * @param boardPaint
     */
    private static void setTemplateLine (BoardPaint boardPaint, XsgTemplate template) {
        //边框样式
        XsgBorder border = template.getBorder();
        int width = template.getWidth();
        int height = template.getHeight();
        //表格的四个边框
        boardPaint.drawLine(0, 0, width, 0, border.getTopStyle(), border.getColor()); //上边框
        boardPaint.drawLine(0, 0, 0, height, border.getLeftStyle(), border.getColor()); //左边框
        boardPaint.drawLine(0, height - 2, width, height - 2, border.getBottomStyle(), border.getColor()); //下边框
        boardPaint.drawLine(width - 2, 0, width - 2, height, border.getRightStyle(), border.getColor()); //右边框
    }

    /**
     * 设置行和单元格信息信息
     * @param boardPaint
     * @param rows
     */
    private static void setRowConfig (BoardPaint boardPaint, int startX1, int startY1, int endX2, List<XsgRow> rows) {
        if (rows == null || rows.isEmpty()) {
            return;
        }

        //画行
        for (XsgRow row : rows) {
            int endY2 = startY1 + row.getHeight();
            XsgBorder border = row.getBorder();
            //画行线，只画底部的线
            boardPaint.drawLine(startX1, endY2, endX2, endY2, border.getBottomStyle(), border.getColor());
            List<XsgCell> cells = row.getCells();
            //画行里面的显示元素
            if (cells != null && !cells.isEmpty()) {
                int cellX1 = startX1;
                int fitWidth = 0;
                for (XsgCell cell : cells) {
                    int cellY1 = startY1, cellY2 = endY2;
                    XsgBorder cellBorder = cell.getBorder();
                    int cellX2 = cellX1 + cell.getWidth();
                    Integer cellFitWidth = setElementConfig(boardPaint, cellX1, cellY1, cellX2, cellY2, cell.getElements());
                    if (cell.getRows() != null && !cell.getRows().isEmpty()) {
                        setRowConfig(boardPaint, cellX1, cellY1, cellX2, cell.getRows());
                    }
                    fitWidth = fitWidth + cellFitWidth;
                    cellX1 = cellX1 + cell.getWidth() + fitWidth;
                    cellX2 = cellX2 + fitWidth;
                    boardPaint.drawLine (cellX2, cellY1, cellX2, cellY2, cellBorder.getRightStyle(), cellBorder.getColor());
                }
            }
            startY1 = startY1 + row.getHeight();
        }
    }

    /**
     * 画显示元素信息
     * @param paint
     * @param elements
     */
    private static int setElementConfig (BoardPaint paint, int startX1, int startY1, int endX2, int endY2, List<XsgElement> elements) {
        if (elements == null || elements.isEmpty()) {
            return 0;
        }
        List<Integer> fitWidths = new ArrayList<>();
        for (XsgElement element : elements) {
            element.setStartCoordinate(new XsgCoordinate().setX(startX1).setY(startY1));
            element.setEndCoordinate(new XsgCoordinate().setX(endX2).setY(endY2));
            Integer fitWidth = ElementProcess.draw(paint, element);
//            if (fitWidth > 0) {
                fitWidths.add(fitWidth);
//            }
        }
        if (fitWidths != null && !fitWidths.isEmpty()) {
            return fitWidths.stream().filter(e -> e != null).max(Comparator.comparing(Function.identity())).get();
        }
        return 0;
    }

    /**
     * 将主数据填充至模版显示元素当中
     * @param template
     * @param param
     */
    private static void setElementValue (XsgTemplate template, Object param) {
        if (param == null) {
            return;
        }
        Map<String, String> paramMap = MapUtil.objectToMap(param);
        setRowCellElementsValue(template.getRows(), paramMap);
    }

    /**
     * 将主数据填充至模版显示元素当中
     * @param rows
     * @param paramMap
     */
    private static void setRowCellElementsValue (List<XsgRow> rows, Map<String, String> paramMap) {
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
                    List<XsgRow> cellRows = cell.getRows();
                    if (elements != null && !elements.isEmpty()) {
                        for (XsgElement element : elements) {
                            if (element != null && element.getFiled() != null && element.getFiled().getName() != null && !element.getFiled().getName().isEmpty()) {
                                String value = paramMap.get(element.getFiled().getName());
                                if (value != null && !value.isEmpty()) {
                                    element.getFiled().setValue(value);
                                } else {
//                                    element.getFiled().setValue(value);
                                }
                            }
                        }
                    }
                    if (cellRows != null && !cellRows.isEmpty()) {
                        setRowCellElementsValue(cellRows, paramMap);
                    }
                }
            }
        }
    }
}
