package com.tedu.element;

import com.tedu.manager.GameLoad;

import javax.swing.*;
import java.util.List;

public class Unbeatable extends Tool{

    public Unbeatable() {
    }
    public Unbeatable(int x, int y, String name)
    {
        this.setX(x);
        this.setY(y);
        ImageIcon icon = GameLoad.imgMap.get(name);
        this.setIcon(icon);
        this.setW(icon.getIconWidth());
        this.setH(icon.getIconHeight());
    }


    @Override
    public void effect(ElementObj obj,long gameTime) {//无敌



        if(this.getisWork()==false) //首次触发效果
        {
            this.setLastTime(gameTime);
            this.setisWork(true);

            obj.setHp(10000);

        }

        if (gameTime-this.getLastTime()<=500) { // 在触发期间
            obj.setHp(10000);
        }
        else//失效了
        {
            obj.setHp(3);//变回初始值
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
