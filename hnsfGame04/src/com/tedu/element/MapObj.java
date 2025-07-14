package com.tedu.element;

import java.awt.Graphics;
import java.util.List;

import javax.swing.ImageIcon;

public class MapObj extends ElementObj{
//	墙需要血量	

	private String name;//墙的type  也可以使用枚举



	@Override
	public void showElement(Graphics g) {
		g.drawImage(this.getIcon().getImage(),
				this.getX(), this.getY(),
				this.getW(),this.getH(),null);
	}

	@Override
	public void die(List<ElementObj> list, int i, long gameTime) {
		list.remove(i);
	}

	@Override   // 如果可以传入   墙类型,x,y
	public ElementObj createElement(String str) {
		System.out.println(str); // 名称,x,y  传进来一个字符串，是key，x，y这样的
		//确认数据传输是对的。 因为只要是使用的配置文件，那么配置文件的格式一定要正确。
//		只要是需要做字符串解析，那么一定要保证字符串的格式是符合要求的
		String []arr=str.split(",");
//		先写一个假图片再说
		ImageIcon icon=null;
		switch(arr[0]) { //设置图片信息 图片还未加载到内存中 //对应墙的种类加载对应图片gitg
		case "GRASS": icon=new ImageIcon("image/wall/grass.png");this.setHp(1);this.setName(arr[0]);break;
		case "BRICK": icon=new ImageIcon("image/wall/brick.png");this.setHp(1);this.setName(arr[0]);break;
		case "RIVER": icon=new ImageIcon("image/wall/river.png");this.setHp(0);this.setName(arr[0]);break;
		case "IRON": icon=new ImageIcon("image/wall/iron.png");this.setHp(10000);this.setName(arr[0]);break;

		}

		int x=Integer.parseInt(arr[1]);
		int y=Integer.parseInt(arr[2]);
		int w=icon.getIconWidth();
		int h=icon.getIconHeight();
		this.setH(h);
		this.setW(w);
		this.setX(x);
		this.setY(y);
		this.setIcon(icon);
		return this;
	}
	@Override  //说明 这个设置扣血等的方法 需要自己思考重新编写。
		public void setLive(boolean live) {
//			被调用一次 就减少一次血。
//			if("IRON".equals(name)) {// 水泥墙需要4下
//				this.setHp(this.getHp()-1);
//				if(this.getHp() >0) {
//					return;
//				}
//			}
			super.setLive(live);
		}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}




