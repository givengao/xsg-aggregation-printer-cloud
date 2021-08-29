package com.xsg.common.printer.action;

import com.xsg.common.printer.constants.BaseConstants;
import com.xsg.common.printer.constants.VoucherConstants;
import com.xsg.common.printer.utils.ImageUtil;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.UUID;

/**
 * @author 高总辉
 * @desc 图片画板
 * @date 2020/11/26 4:48 下午
 */
public class ImageBoard extends VoucherBoard<Graphics2D> {

    /**
     * 打印文件存放路径
     */
    private static final String printPath = VoucherConstants.PRINT_PATH;

    public BufferedImage image;

    public Graphics2D g;

    public ImageBoard(int height, int width, int bgColor) {
        super(height, width, bgColor);
    }

    @Override
    public BoardPaint<Graphics2D> createBoard() {
        //新建图片
        image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        g = image.createGraphics();
        //设置背景图片
        g.setColor(BaseConstants.getColor(getBgColor()));
        //设置颜色区域大小
        g.fillRect(0, 0, getWidth(), getHeight());
        //消除文本出现锯齿现象
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        return new ImageBoardPaint(image.createGraphics());
    }

    @Override
    public String getResult() {
        String fileName = UUID.randomUUID().toString() + ".png";
        File file = new File(printPath, fileName);
        ImageUtil.createImage(image, file);
        g.dispose();
        return file.getPath();
    }
}
