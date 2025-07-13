package com.tedu.element;

import javax.swing.*;
import java.awt.*;

public class Tank extends ElementObj{ //坦克类 ，用于判断

    public Tank() {
    }

    public Tank(int x, int y, int w, int h, ImageIcon icon) {
        super(x, y, w, h, icon);

    }

    private String fx="up";

    public String getFx() {
        return fx;
    }

    public void setFx(String fx) {
        this.fx = fx;
    }
    @Override
    public void showElement(Graphics g) {
//		绘画图片
        g.drawImage(this.getIcon().getImage(),
                this.getX(), this.getY(),
                this.getW(), this.getH(), null);
    }
}
