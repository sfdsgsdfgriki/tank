package com.tedu.show;

import com.tedu.controller.GameListener;
import com.tedu.controller.GameThread;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class StartJFrame extends JFrame
{

    private JButton startButton;

    public StartJFrame(){init();}
    private void init()
    {
        // 设置窗口基本属性
        this.setTitle("坦克大战 - 开始游戏");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        // 加载背景图片
        ClassLoader classLoader = getClass().getClassLoader();
        URL imageUrl = classLoader.getResource("login_background.png");
        ImageIcon backgroundImage = null;
        if (imageUrl != null) {
            backgroundImage = new ImageIcon(imageUrl);
            System.out.println("背景图片加载成功");
        } else {
            System.out.println("背景图片加载失败");
        }

        // 创建内容面板
        ImageIcon finalBackgroundImage = backgroundImage;
        JPanel contentPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (finalBackgroundImage != null) {
                    g.drawImage(finalBackgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
                }
            }
        };

        // 创建开始按钮
        startButton = new JButton("开始游戏");
        startButton.setFont(new Font("宋体", Font.BOLD, 24));
        startButton.setPreferredSize(new Dimension(200, 60));
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 点击按钮后关闭当前窗口并启动游戏
                dispose(); // 关闭启动窗口
                startGame(); // 启动游戏主窗口
            }
        });

        // 创建按钮面板并居中放置按钮
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false); // 设置为透明面板
        buttonPanel.add(startButton);

        // 添加组件到内容面板
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        // 设置内容面板并显示窗口
        setContentPane(contentPanel);
        setVisible(true);
    }

    private void startGame() {
        // 创建游戏主窗口
        GameJFrame gj = new GameJFrame();
        GameMainJPanel gamePanel = new GameMainJPanel();

        // 设置游戏面板
        gj.setjPanel(gamePanel);

        // 添加键盘监听
        GameListener listener = new GameListener();
        gj.setKeyListener(listener);

        // 启动游戏线程
        GameThread gameThread = new GameThread();
        gameThread.setGameMainJPanel(gamePanel); //将面板存入主线程
        gj.setThead(gameThread);


        // 显示游戏窗口
        gj.start();
    }
}
