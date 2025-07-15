package com.tedu.element;

import com.tedu.manager.GameLoad;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Recover extends Tool{

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
    public void effect(ElementObj obj,long gameTime) {


        obj.setHp(3);
        this.setCanRemove(true);
    }

    @Override
    public void die(List<ElementObj> list, int i, long gameTime) {

        list.remove(i);
    }



}
