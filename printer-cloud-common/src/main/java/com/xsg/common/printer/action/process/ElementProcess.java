package com.xsg.common.printer.action.process;


import com.xsg.common.printer.action.BoardPaint;
import com.xsg.common.printer.model.XsgElement;

import java.util.HashMap;
import java.util.Map;


/**
 * @author 高总辉
 * @desc
 * @date 2020/11/23 7:08 下午
 */
public abstract class ElementProcess<T extends XsgElement> {

    private static Map<String, ElementProcess<? extends XsgElement>> elementProcessMap = new HashMap<>();

    static {
        elementProcessMap.put("1", new FontElementProcess());
        elementProcessMap.put("2", new ImageElementProcess());
        elementProcessMap.put("3", new QrCodeElementProcess());
        elementProcessMap.put("4", new BarcodeElementProcess());
    }

    abstract int execute (BoardPaint paint, T element);

    public static int draw (BoardPaint paint, XsgElement element) {
        if (element == null) {
            return 0;
        }
        ElementProcess elementProcess = elementProcessMap.get(String.valueOf(element.getType()));
        return elementProcess.execute(paint, element);
    }
}
