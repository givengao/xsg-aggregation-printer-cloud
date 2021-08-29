package com.xsg.common.printer.action;

import com.itextpdf.awt.PdfGraphics2D;
import com.itextpdf.text.*;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import com.xsg.common.printer.constants.VoucherConstants;
import com.xsg.common.printer.utils.FileWriterUtils;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

/**
 * @author 高总辉
 * @desc
 * @date 2020/12/4 4:23 下午
 */
public class PdfBoard extends VoucherBoard<Graphics2D>{

    /**
     * 打印文件存放路径
     */
    private static final String printPath = VoucherConstants.PRINT_PATH;

    private Document document;

    private PdfContentByte canvas;

    private PdfTemplate template;

    private Graphics2D g2d;

    private String pdfPath;

    public PdfBoard(int height, int width, int bgColor) {
        super(height, width, bgColor);
    }

    @Override
    public BoardPaint<Graphics2D> createBoard() {
        try {
            Rectangle rectangle = new Rectangle(getWidth(),  getHeight());
            document = new Document(rectangle);
            String pdfName = UUID.randomUUID().toString()+ ".pdf";
            pdfPath = printPath + "/" + pdfName;
            File file = new File(printPath, pdfName);
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(FileWriterUtils.createIfNotExists(file)));
            document.open();
            canvas = writer.getDirectContent();
            template = canvas.createTemplate(getWidth(), getHeight());   //绘制图形模板
            g2d = new PdfGraphics2D(template, getWidth(), getHeight(), true);
            return new ImageBoardPaint(g2d);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public String getResult() {
        canvas.addTemplate(template, 0, 0);
        g2d.dispose();
        document.close();
        return pdfPath;
    }
}
