package com.tedu.element;

import com.tedu.manager.GameLoad;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Recover extends ElementObj{

    public Recover() {
    }

    public Recover(int x, int y, String name) {
        this.setX(x);
        this.setY(y);
        ImageIcon icon = GameLoad.imgMap.get(name);
        this.setIcon(icon);
        this.setW(icon.getIconWidth());
        this.setH(icon.getIconHeight());
    }

    @Override
    public void effect(ElementObj obj) {
        obj.setHp(33);
    }

    @Override
    public void die(List<ElementObj> list, int i, long gameTime) {

        list.remove(i);
    }

    @Override
    public void showElement(Graphics g) {
//		绘画图片
        g.drawImage(this.getIcon().getImage(),
                this.getX(), this.getY(),
                this.getW(), this.getH(), null);
    }

}
