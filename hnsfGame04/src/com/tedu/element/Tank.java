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

    private long lastShotTime=0;
    private boolean isFire =false; // 开火状态，true 发射子弹，false 不发射

    private int fireSpeed;//子弹速度默认是5
    public String getFx() {
        return fx;
    }

    public void setFx(String fx) {
        this.fx = fx;
    }


    public long getLastShotTime() {
        return lastShotTime;
    }

    public void setLastShotTime(long lastShotTime) {
        this.lastShotTime = lastShotTime;
    }

    public boolean getisFire() {
        return isFire;
    }

    public void setisFire(boolean fire) {
        isFire = fire;
    }

    public int getFireSpeed() {
        return fireSpeed;
    }

    public void setFireSpeed(int fireSpeed) {
        this.fireSpeed = fireSpeed;
    }

    @Override
    public void showElement(Graphics g) {
//		绘画图片
        g.drawImage(this.getIcon().getImage(),
                this.getX(), this.getY(),
                this.getW(), this.getH(), null);
    }
}
