package com.xsg.common.printer.core;


import com.xsg.common.printer.constants.FontSizeEnum;
import com.xsg.common.printer.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author 高总辉
 * @desc
 * @date 2020/11/30 10:18 上午
 */
public class TemplateFluent {

    public XsgTemplate template;

    public Row row;

    public static TemplateFluent template() {
        return new TemplateFluent();
    }

    public Row start(int height, int width, int bgColor, boolean isHidden) {
        template = new XsgTemplate();
        template.setHeight(height);
        template.setWidth(width);
        template.setColor(bgColor);

        XsgBorder border = new XsgBorder();
        if (isHidden) {
            border.setTopStyle(0);
            border.setRightStyle(0);
            border.setLeftStyle(0);
            border.setBottomStyle(0);
            border.setColor(2);
        } else {
            border.setTopStyle(1);
            border.setRightStyle(1);
            border.setLeftStyle(1);
            border.setBottomStyle(1);
            border.setColor(2);
        }
        template.setBorder(border);

        row = new RowImpl();
        return row;
    }

    public XsgTemplate end() {
        template.setRows(row.get());
        return template;
    }

    public interface Row {

        static Row create() {
            return new RowImpl();
        }

        Row row (int height, Cell cell);

        Row rowH (int height, Cell cell);

        Row rowX (int height, Cell cell);

        List<XsgRow> get ();
    }

    public interface Cell {

        static Cell create() {
            return new CellImpl();
        }

        Cell cell (int width, Element element);

        Cell cellH (int width, Element element);

        Cell cellX (int width, Element element);

        Cell cell (int width, Row row);

        Cell cellH (int width, Row row);

        Cell cellX (int width, Row row);

        List<XsgCell> get();
    }

    public interface Element {

        static Element create() {
            return new ElementImpl();
        }

        Element texBElement(XsgFiled filed, XsgMargin margin, FontSizeEnum fontSizeEnum);

        Element textElement (XsgFiled filed, XsgMargin margin, FontSizeEnum fontSizeEnum);

        Element imageElement (XsgFiled filed, XsgMargin margin, int height, int width);

        Element qrCodeElement (XsgFiled filed, XsgMargin margin, int height, int width);

        Element barCodeElement (XsgFiled filed, XsgMargin margin, int height, int width);

        List<XsgElement> get();
    }

    public static class RowImpl implements Row {

        private List<XsgRow> rows;

        public RowImpl() {
            this.rows = new ArrayList<>();
        }

        @Override
        public Row row(int height, Cell cell) {
            row(height, false, false, cell);
            return this;
        }

        @Override
        public Row rowH(int height, Cell cell) {
            row(height, true, false, cell);
            return this;
        }

        @Override
        public Row rowX(int height, Cell cell) {
            row(height, false, true, cell);
            return this;
        }


        public Row row(int height, boolean hiddenBorder, boolean dotted, Cell cell) {
            Objects.requireNonNull(cell, "cell can not be null");
            List<XsgCell> cells = cell.get();
            Objects.requireNonNull(cells, "cells can not be null");
            XsgRow xsgRow = getXsgRow(height, hiddenBorder);
            if (dotted) {
                xsgRow.getBorder().setBottomStyle(2)
                        .setTopStyle(2)
                        .setLeftStyle(2)
                        .setRightStyle(2);
            }
            xsgRow.setCells(cells);
            rows.add(xsgRow);
            return this;
        }

        @Override
        public List<XsgRow> get() {
            return rows;
        }

        private XsgRow getXsgRow (int height, boolean hiddenBorder) {
            return PrintTemplate.getDefaultRow(height, hiddenBorder);
        }
    }

    public static class CellImpl implements Cell {

        private List<XsgCell> cells;

        public CellImpl() {
            this.cells = new ArrayList<>();
        }

        @Override
        public Cell cell(int width, Element element) {
            cell(width, false, false,element, null);
            return this;
        }

        @Override
        public Cell cellH(int width, Element element) {
            cell(width, true, false, element, null);
            return this;
        }

        @Override
        public Cell cellX(int width, Element element) {
            cell(width, false, true, element, null);
            return this;
        }

        @Override
        public Cell cell(int width, Row row) {
            cell(width, false, false, null, row);
            return this;
        }

        @Override
        public Cell cellH(int width, Row row) {
            cell(width, true, false, null, row);
            return this;
        }

        @Override
        public Cell cellX(int width, Row row) {
            cell(width, false, true, null, row);
            return this;
        }

        public Cell cell(int width, boolean hiddenBorder, boolean dotted, Element element, Row row) {
            if (width <= 0) {
                return null;
            }
            XsgCell xsgCell = PrintTemplate.getDefaultCell(width, hiddenBorder);
            if (element!= null && element.get() != null && !element.get().isEmpty()) {
                xsgCell.setElements(element.get());
            }
            if (row != null && row.get() != null && !row.get().isEmpty()) {
                xsgCell.setRows(row.get());
            }
            if (dotted) {
                xsgCell.getBorder().setBottomStyle(2)
                        .setTopStyle(2)
                        .setLeftStyle(2)
                        .setRightStyle(2);
            }
            cells.add(xsgCell);
            return this;
        }

        @Override
        public List<XsgCell> get() {
            return cells;
        }
    }

    public static class ElementImpl implements Element {

        private List<XsgElement> elements;

        public ElementImpl() {
            this.elements = new ArrayList<>();
        }

        public Element texElement(XsgFiled filed, XsgMargin margin, boolean bold, FontSizeEnum fontSizeEnum) {
            XsgFontElement xsgElement = new XsgFontElement();
            xsgElement.setType(1);
            xsgElement.setFontSizeEnum(fontSizeEnum);
            xsgElement.setFontStyle(0);
            xsgElement.setFontNameType(1);
            xsgElement.setBold(bold);
            xsgElement.setFiled(filed);
            xsgElement.setMargin(margin);
            elements.add(xsgElement);
            return this;
        }

        @Override
        public Element texBElement(XsgFiled filed, XsgMargin margin, FontSizeEnum fontSizeEnum) {
            return texElement(filed, margin, true, fontSizeEnum);
        }

        @Override
        public Element textElement(XsgFiled filed, XsgMargin margin, FontSizeEnum fontSizeEnum) {
            return texElement(filed, margin, false, fontSizeEnum);
        }

        @Override
        public Element imageElement(XsgFiled filed, XsgMargin margin, int height, int width) {
            XsgImageElement xsgElement = new XsgImageElement();
            xsgElement.setType(2);
            xsgElement.setFiled(filed);
            xsgElement.setMargin(margin);
            xsgElement.setHeight(height);
            xsgElement.setWidth(width);
            elements.add(xsgElement);
            return this;
        }

        @Override
        public Element qrCodeElement(XsgFiled filed, XsgMargin margin, int height, int width) {
            XsgQrCodeElement xsgElement = new XsgQrCodeElement();
            xsgElement.setType(3);
            xsgElement.setFiled(filed);
            xsgElement.setMargin(margin);
            xsgElement.setHeight(height);
            xsgElement.setWidth(width);
            elements.add(xsgElement);
            return this;
        }

        @Override
        public Element barCodeElement(XsgFiled filed, XsgMargin margin, int height, int width) {
            XsgBarcodeElement xsgElement = new XsgBarcodeElement();
            xsgElement.setType(4);
            xsgElement.setFiled(filed);
            xsgElement.setMargin(margin);
            xsgElement.setHeight(height);
            xsgElement.setWidth(width);

            elements.add(xsgElement);
            return this;
        }

        @Override
        public List<XsgElement> get() {
            return elements;
        }
    }
}
