package com.tedu.element;

import com.tedu.manager.GameLoad;

import javax.swing.*;
import java.util.List;

public class FasterBullet extends Tool{


    public FasterBullet() {
    }
    public FasterBullet(int x, int y, String name)
    {
        this.setX(x);
        this.setY(y);
        ImageIcon icon = GameLoad.imgMap.get(name);
        this.setIcon(icon);
        this.setW(icon.getIconWidth());
        this.setH(icon.getIconHeight());

    }

    @Override
    public void effect(ElementObj obj, long gameTime) {

        if(obj instanceof Tank)
        if(this.getisWork()==false) //首次触发效果
        {
            this.setLastTime(gameTime);
            this.setisWork(true);

            ((Tank) obj).setFireSpeed(20);

        }

        if (gameTime-this.getLastTime()<=400) { // 在触发期间
            ((Tank) obj).setFireSpeed(20);
        }
        else//失效了
        {
            ((Tank) obj).setFireSpeed(5);
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
