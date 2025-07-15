package com.tedu.element;

import com.tedu.manager.GameLoad;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Boat extends Tool{

    public Boat() {
    }

    public Boat(int x, int y, String name) {
        this.setX(x);
        this.setY(y);
        ImageIcon icon = GameLoad.imgMap.get(name);
        this.setIcon(icon);
        this.setW(icon.getIconWidth());
        this.setH(icon.getIconHeight());


    }

    @Override
    public void effect(ElementObj obj,long gameTime) {//增加移速



           if(this.getisWork()==false) //首次触发效果
           {
               this.setLastTime(gameTime);
               this.setisWork(true);

               obj.setMoveNum(4);

           }

           if (gameTime-this.getLastTime()<=500) { // 在触发期间
               obj.setMoveNum(4);
           }
           else//失效了
           {
               obj.setMoveNum(2);
               this.setisWork(false);
               //obj.removeTool(this);
               this.setCanRemove(true); //失效了就加这句
           }


    }

    @Override
    public void die(List<ElementObj> list, int i, long gameTime) {

        list.remove(i);
    }




}
