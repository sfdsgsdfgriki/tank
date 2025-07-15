package com.tedu.manager;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import javax.swing.ImageIcon;

import com.tedu.element.*;

/**
 * @说明  加载器(工具：用户读取配置文件的工具)工具类,大多提供的是 static方法
 * @author renjj
 *
 */
public class GameLoad {
//	得到资源管理器
	private static ElementManager em=ElementManager.getManager();
	
//	图片集合  使用map来进行存储     枚举类型配合移动(扩展)
	public static Map<String,ImageIcon> imgMap = new HashMap<>();

	public static List<String> mapPoint = new ArrayList<>();

	//public static Map<String,List<ImageIcon>> imgMaps;

//	用户读取文件的类
	//Properties 是 Java 中用于存储键值对配置的类，
// 特别适合处理配置文件（如 .properties 文件）。它继承自 Hashtable<Object, Object>
	//将配置文件的键值对读到该对象中
	private static Properties pro =new Properties();
	/**
	 * @说明 传入地图id有加载方法依据文件规则自动产生地图文件名称，加载文件
	 * @param mapId  文件编号 文件id
	 */
	public static void MapLoad(int mapId) {
//		得到啦我们的文件路径
		String mapName="com/tedu/text/"+mapId+".map";
//		使用io流来获取文件对象   得到类加载器
		ClassLoader classLoader = GameLoad.class.getClassLoader();
		//Class 对象的 getClassLoader() 方法返回加载该类的类加载器


		InputStream maps = classLoader.getResourceAsStream(mapName);
		//classLoader.getResourceAsStream(mapName) 根据路径在 classpath 中查找文件，
		// 返回一个输入流（InputStream）供程序读取文件内容

		/*
		在 InputStream maps = classLoader.getResourceAsStream(mapName);
		 执行后，配置文件的内容并未直接 “存放” 在某个固定位置，而是通过 InputStream
		 建立了一个通往文件内容的 “读取通道”

		 */
		if(maps ==null) {
			System.out.println("配置文件读取异常,请重新安装");
			return;
		}
		try {
//			以后用的 都是 xml 和 json
			pro.clear();
			pro.load(maps); //参数是inputstream 将配置文件中的键值对加载到Properties 对象pro中
//			可以直接动态的获取所有的key，有key就可以获取 value
//			java学习中最好的老师 是 java的API文档。
			//Enumeration<?> names = pro.propertyNames();


			Set<Object> set = pro.keySet();//获取 Properties 对象读取到配置文件中的所有键名（key）
			for (Object o:set)//遍历每一个key 一个key，就是一个种类的墙壁 如水，铁，草地
			{
				String key = o.toString();
				String [] arrs=pro.getProperty(key).split(";");
				//拿到一种墙壁的所有坐标，然后对每个坐标分割，分割完变成一个字符串数组，每一个单元是x，y的字符串
				//先拿到值，   再对值分割 该键对应值的数组，多个坐标，用;分割 arrs[i]是x,y的形式

				for(int i=0;i<arrs.length;i++) {//一个数组元素是一个X，y的形式，这里再对每个坐标拆分，然后创建对应坐标的每块墙
					ElementObj element = new MapObj().createElement(key+","+arrs[i]);
					//创造地图的一块
					System.out.println(element);
					em.addElement(element, GameElement.MAPS);
					//mapPoint.add(arrs[i]);

				}

			}


		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}




	/**
	 *@说明 加载图片代码
	 *加载图片 代码和图片之间差 一个 路径问题 
	 */


	public static void loadImg() {//可以带参数，因为不同的关也可能需要不一样的图片资源
		String texturl="com/tedu/text/GameData.pro";//文件的命名可以更加有规律
		ClassLoader classLoader = GameLoad.class.getClassLoader();
		InputStream texts = classLoader.getResourceAsStream(texturl);
//		imgMap用于存放图片
		pro.clear();
		try {
//			System.out.println(texts);
			pro.load(texts);
			Set<Object> set = pro.keySet();//是一个set集合
			for(Object o:set) {
				String url=pro.getProperty(o.toString());


				imgMap.put(o.toString(), new ImageIcon(url));//加载对应路径的图片 在这里加载了

			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	/**
	 * 加载玩家
	 */
	public static void loadPlay() {
		//loadObj();
		String play1Str="500,500,up";
		String play2Str ="50,50,left";
//		ElementObj obj=getObj("play");
//		ElementObj play = obj.createElement(playStr);

		ElementObj play1 = new Play1().createElement(play1Str);
		em.addElement(play1, GameElement.PLAY);
		ElementObj play2 = new Play2().createElement(play2Str);

//		解耦,降低代码和代码之间的耦合度 可以直接通过 接口或者是抽象父类就可以获取到实体对象

		em.addElement(play2, GameElement.PLAY);

//		ElementObj enemy1 = new Enemy().createElement("");

//


	}

	public static void loadEnemy()
	{
		List<ElementObj> maplist = em.getElementsByKey(GameElement.MAPS);
		List<ElementObj> playList = em.getElementsByKey(GameElement.PLAY);
		Random ran=new Random();
		int enemyNum = 3;
		System.out.println(maplist);
		System.out.println(playList);

		while (enemyNum>0) {

			int x = ran.nextInt(700);
			int y = ran.nextInt(500);
//

			ElementObj enemy = new Enemy(x, y);

			boolean flag =true; //是真的说明没有与墙壁相交 每个对象都设一个flag

			for (ElementObj mapobj : maplist) {


				if(flag==false) break; //有一个相交都要重新来过

				for (ElementObj playobj : playList) {

//					System.out.println(enemy.getRectangle());
//					System.out.println(mapobj.getRectangle());

					if (enemy.pk(mapobj) || enemy.pk(playobj))
					{
						flag = false;
						break;
					}

				}

			}

			if(flag==true)
			{
				enemyNum--;
				System.out.println(enemyNum);
				em.addElement(enemy,GameElement.ENEMY);
			}





		}

	}


	
	public static ElementObj getObj(String str) {
		try {
			Class<?> class1 = objMap.get(str);
			Object newInstance = class1.newInstance();
			if(newInstance instanceof ElementObj) {
				return (ElementObj)newInstance;   //这个对象就和 new Play()等价
//				新建立了一个叫  GamePlay的类
			}
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 扩展： 使用配置文件，来实例化对象 通过固定的key(字符串来实例化)
	 * @param args
	 */
	private static Map<String,Class<?>> objMap=new HashMap<>();
	
	public static void loadObj() {
		String texturl="com/tedu/text/obj.pro";//文件的命名可以更加有规律
		ClassLoader classLoader = GameLoad.class.getClassLoader();
		InputStream texts = classLoader.getResourceAsStream(texturl);
		pro.clear();
		try {
			pro.load(texts);
			Set<Object> set = pro.keySet();//是一个set集合
			for(Object o:set) {
				String classUrl=pro.getProperty(o.toString());
//				使用反射的方式直接将 类进行获取
				Class<?> forName = Class.forName(classUrl);
				objMap.put(o.toString(), forName);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
//	用于测试
	public static void main(String[] args) {

		MapLoad(5);
	}
	
	
	
	
	
	
	
	
	
	
}
