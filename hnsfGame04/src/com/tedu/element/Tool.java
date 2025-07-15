package com.tedu.element;

import java.awt.*;

public class Tool extends ElementObj{


    public void effect(ElementObj obj,long gameTime){}

    private boolean isWork = false;//道具是否已经生效 没生效为false，生效了为true

    private boolean canRemove = false;

    public boolean getCanRemove() {
        return canRemove;
    }


    public void setCanRemove(boolean canRemove) {
        this.canRemove = canRemove;
    }

    @Override
    public void showElement(Graphics g) {
//		绘画图片
        g.drawImage(this.getIcon().getImage(),
                this.getX(), this.getY(),
                this.getW(), this.getH(), null);
    }

    public boolean getisWork() {
        return isWork;
    }

    public void setisWork(boolean work) {
        isWork = work;
    }
}
