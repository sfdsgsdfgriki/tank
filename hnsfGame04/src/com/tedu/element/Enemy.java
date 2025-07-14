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

	private boolean isMove=false;

	@Override
	public ElementObj createElement(String str) {
		Random ran=new Random();
		int x=ran.nextInt(700);
		int y=ran.nextInt(500);
		this.setX(x);
		this.setY(y);

		ImageIcon icon = GameLoad.imgMap.get(this.getClass().getSimpleName()+"."+this.getFx());
		this.setIcon(icon);
		this.setW(icon.getIconWidth());
		this.setH(icon.getIconHeight());
		this.setHp(1);

		return this;
	}

	public Enemy(int x, int y) {

		this.setX(x);
		this.setY(y);

		ImageIcon icon = GameLoad.imgMap.get(this.getClass().getSimpleName()+"."+this.getFx());
		this.setIcon(icon);
		this.setW(icon.getIconWidth());
		this.setH(icon.getIconHeight());
		this.setHp(1);
		this.setMoveNum(2);
		this.setAttack(1);

	}

	@Override
	public void move(long gameTime) {
		System.out.println(gameTime+" "+this.getLastTime());
		//gameTime-this.getLastTime()<=300

		if (gameTime-this.getLastTime()<50) this.isMove=true;

		else  {
			this.isMove=false;
			this.setLastTime(gameTime);

			this.left = false;
			this.right = false;
			this.up = false;
			this.down = false;

			Random ran = new Random();
			int r = ran.nextInt(4);
			System.out.println("r: "+r);
			switch (r)
			{
				case 0:this.setFx("left");break;
				case 1:this.setFx("right");break;
				case 2:this.setFx("up");break;
				case 3:this.setFx("down");break;
			}
		}

		System.out.println(this.isMove);

		if(this.isMove==true) { //正在移动

			System.out.println(this.getFx());

			switch (this.getFx()) {//当前方向
				case "left":
					this.left = true;
					System.out.println(this.getX());;break;
				case "right":
					this.right = true;
					System.out.println(this.getX());break;
				case "up":
					this.up = true;
					System.out.println(this.getY());break;
				case "down":
					this.down = true;
					System.out.println(this.getY());break;
			}


			if (this.left && this.getX() > 0) {
				this.setX(this.getX() - this.getMoveNum());
			}
			if (this.up && this.getY() > 0) {
				this.setY(this.getY() - this.getMoveNum());
			}
			if (this.right && this.getX() < 800 - this.getW() - 14) {  //坐标的跳转由大家来完成
				this.setX(this.getX() + this.getMoveNum());
			}
			if (this.down && this.getY() < 600 - this.getH() - 35) {
				this.setY(this.getY() + this.getMoveNum());
			}

			}
	}

	@Override
	protected void add(long gameTime) {
		if (gameTime-this.getLastShotTime()<500)
		{
			this.setisFire(true);


		}
		else{
			this.setLastShotTime(gameTime);
			this.setisFire(false);

		}


		if (this.getisFire()==true)
		{

			ElementObj element = new PlayFile().createElement(this.toString());
			ElementManager.getManager().addElement(element, GameElement.PLAYFILE);
		}


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
	@Override
	protected void updateImage() {
//		ImageIcon icon=GameLoad.imgMap.get(fx);
//		System.out.println(icon.getIconHeight());//得到图片的高度
//		如果高度是小于等于0 就说明你的这个图片路径有问题
		this.setIcon(GameLoad.imgMap.get(this.getClass().getSimpleName()+"."+this.getFx()));

	}

	@Override
	public String toString() {// 这里是偷懒，直接使用toString；建议自己定义一个方法
		// 这是子弹生成的位置，返回一个字符串给子弹创建方法
		//  {X:3,y:5,f:up,t:A} json格式
		//这里给子弹赋值坦克的攻击力
		int x=this.getX();
		int y=this.getY();
		switch(this.getFx()) { // 子弹在发射时候就已经给予固定的轨迹。可以加上目标，修改json格式
			case "up": x+=13;y-=11;break;
			// 一般不会写20等数值，一般情况下 图片大小就是显示大小；一般情况下可以使用图片大小参与运算
			case "left": x-=13;y+=14;break;
			case "right": x+=36;y+=14;break;
			case "down": x+=13;y+=36; break;
		}//个人认为： 玩游戏有助于 理解面向对象思想;不能专门玩，需要思考，父类应该怎么抽象，子类应该怎么实现
//		学习技术不犯法，但是不要用技术做犯法的事.
		return "x:"+x+",y:"+y+",f:"+this.getFx()+",attack:"+this.getAttack();
	}
}
