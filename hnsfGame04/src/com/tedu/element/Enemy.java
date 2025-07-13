package com.tedu.element;

import com.tedu.manager.ElementManager;
import com.tedu.manager.GameElement;
import com.tedu.manager.GameLoad;

import java.awt.Graphics;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;

public class Enemy extends Tank{

	private boolean left=false; //左
	private boolean up=false;   //上
	private boolean right=false;//右
	private boolean down=false; //下

	@Override
	public ElementObj createElement(String str) {
		Random ran=new Random();
		int x=ran.nextInt(800);
		int y=ran.nextInt(500);
		this.setX(x);
		this.setY(y);
		this.setW(50);
		this.setH(50);
		this.setIcon(new ImageIcon("image/tank/bot/bot_up.png"));
		this.setHp(1);
		return this;
	}

	@Override
	protected void move() {

	}

	@Override
	public void die(List<ElementObj> list, int i, long gameTime) {

		super.die(list, i, gameTime);

		this.setIcon(GameLoad.imgMap.get("Boom"));
		if(gameTime - this.getDieTime()>=5)
		{
			list.remove(i); //移出去就不显示了
			Random ran = new Random();
			int r =ran.nextInt(2);
			ElementObj obj = null;
			switch (r)
			{
				case 0:  obj = new Boat(this.getX(),this.getY(),"boat");break;
				case 1:  obj = new Recover(this.getX(),this.getY(),"recover");break;
			}
			System.out.println(obj.getClass());
			System.out.println(r);
			ElementManager.getManager().addElement(obj, GameElement.TOOL);

		}

	}



}
