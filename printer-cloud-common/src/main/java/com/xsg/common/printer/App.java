package com.xsg.common.printer;

import com.alibaba.fastjson.JSON;
import com.xsg.common.printer.action.VoucherHandler;
import com.xsg.common.printer.template.TemplateConstants;
import com.xsg.common.printer.constants.VoucherEnum;
import com.xsg.common.printer.covert.ElementCovert;
import com.xsg.common.printer.model.XsgTemplate;
import com.xsg.common.printer.utils.ImageUtil;
import com.xsg.common.printer.vo.TemplateVo;

import javax.swing.*;
import java.awt.*;

/**
 * @author 高总辉
 * @desc
 * @date 2020/11/23 4:39 下午
 */
public class App {


    public static void main(String[] args) {
        /*
         * 在 AWT 的事件队列线程中创建窗口和组件, 确保线程安全,
         * 即 组件创建、绘制、事件响应 需要处于同一线程。
         */
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                // 创建窗口对象
                MyFrame frame = new MyFrame();
                // 显示窗口
                frame.setVisible(true);
            }
        });
    }

    /**
     * 窗口
     */
    public static class MyFrame extends JFrame {

        public static final String TITLE = "Java图形绘制";

        public static final int WIDTH = 2000;
        public static final int HEIGHT = 2000;

        public MyFrame() {
            super();
            initFrame();
        }

        private void initFrame() {
            // 设置 窗口标题 和 窗口大小
            setTitle(TITLE);
            setSize(WIDTH, HEIGHT);

            // 设置窗口关闭按钮的默认操作(点击关闭时退出进程)
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            // 把窗口位置设置到屏幕的中心
            setLocationRelativeTo(null);

            // 设置窗口的内容面板
            MyPanel panel = new MyPanel(this);
            setContentPane(panel);
        }

    }

    /**
     * 内容面板
     */
    public static class MyPanel extends JPanel {

        private MyFrame frame;

        public MyPanel(MyFrame frame) {
            super();
            this.frame = frame;
        }

        /**
         * 绘制面板的内容: 创建 JPanel 后会调用一次该方法绘制内容,
         * 之后如果数据改变需要重新绘制, 可调用 updateUI() 方法触发
         * 系统再次调用该方法绘制更新 JPanel 的内容。
         */
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            String path = draw();
            if (path == null) {
                return;
            }
            try {
                Image image = ImageUtil.readImage(path, 800, 400, false);
                g.drawImage(image, 200, 10, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static String draw() {
        TemplateVo templateVo = new TemplateVo();
        templateVo.setCname("小高快送");
//        System.out.println(VoucherHandler.print(TemplateConstants.getQsTemplate(), VoucherEnum.INSTRUCT_TSPL_HPRT, templateVo));
//        System.out.println(VoucherHandler.print(TemplateConstants.getQsTemplate(), VoucherEnum.INSTRUCT_CPCL, templateVo));
//        VoucherHandler.print(TemplateConstants.getPkgTemplate(), VoucherEnum.PDF, templateVo);
//        toPrint(TemplateConstants.getPkgTemplate());
//        toPrint(TemplateConstants.getQsTemplate());
        return VoucherHandler.print(TemplateConstants.getTuHuoPkgTemplate(), VoucherEnum.IMAGE, templateVo);
    }

    private static void toPrint (XsgTemplate template) {
        System.out.println(JSON.toJSONString(ElementCovert.toAll(template), true));
    }
}
