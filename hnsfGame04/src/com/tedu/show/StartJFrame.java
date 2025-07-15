package com.tedu.show;

import com.tedu.controller.GameListener;
import com.tedu.controller.GameThread;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class StartJFrame extends JFrame {

    private JButton startButton;
    private int mapId = 1; // 地图ID

    public StartJFrame() {
        init();
    }

    private void init() {
        // 窗口基本设置
        this.setTitle("坦克大战 - 选择地图");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        // 加载背景图片
        ClassLoader classLoader = getClass().getClassLoader();
        URL imageUrl = classLoader.getResource("login_background.png");
        ImageIcon backgroundImage = (imageUrl != null) ? new ImageIcon(imageUrl) : null;

        // 内容面板使用 GridBagLayout 布局（核心布局）
        ImageIcon finalBackgroundImage = backgroundImage;
        JPanel contentPanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (finalBackgroundImage != null) {
                    g.drawImage(finalBackgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
                }
                else System.out.println("图片加载失败");
            }
        };

        // GridBagConstraints 用于控制组件位置
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; // 所有组件都放在第0列（垂直对齐）
        gbc.anchor = GridBagConstraints.CENTER; // 组件水平居中
        gbc.insets = new Insets(0, 0, 20, 0); // 组件上下边距（增加垂直间隔）

        // 1. 创建3x3按键网格面板
        JPanel buttonGridPanel = new JPanel(new GridLayout(3, 3, 15, 15));
        buttonGridPanel.setOpaque(false);

        // 添加9个地图按键
        for (int i = 1; i <= 9; i++)
        {
            JButton button = new JButton("地图 " + i);
            button.setFont(new Font("宋体", Font.BOLD, 18));
            button.setPreferredSize(new Dimension(100, 60));
            int idx = i;
            button.addActionListener(e -> {
                mapId = idx;
                System.out.println("选择地图：" + mapId);
                // 选地图
                for (Component comp : buttonGridPanel.getComponents()) {
                    ((JButton) comp).setBackground(null);
                }
                button.setBackground(Color.GREEN);
            });
            buttonGridPanel.add(button);
        }

        // 2. 将按键网格面板添加到内容面板（核心位置调整）
        gbc.gridy = 0; // 放在第0行
        gbc.weighty = 0.3; // 垂直权重：按键面板占40%（数值越小，位置越靠下）
        gbc.insets = new Insets(210, 0, 20, 0); // 顶部额外增加100px边距（往下推）
        contentPanel.add(buttonGridPanel, gbc);

        // 3. 开始按钮
        startButton = new JButton("开始游戏");
        startButton.setFont(new Font("宋体", Font.BOLD, 24));
        startButton.setPreferredSize(new Dimension(200, 60));
        startButton.addActionListener(e -> {
            dispose();
            startGame();
        });

        // 4. 将开始按钮添加到内容面板（位置在按键下方）
        gbc.gridy = 1; // 放在第1行
        gbc.weighty = 0.6; // 垂直权重：开始按钮区域占60%（拉低整体按键位置）
        gbc.insets = new Insets(20, 0, 50, 0); // 底部留50px边距
        contentPanel.add(startButton, gbc);

        // 显示窗口
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
        gameThread.setMapId(mapId);
        gameThread.setGameMainJPanel(gamePanel); //将面板存入主线程
        gj.setThead(gameThread);

        // 显示游戏窗口
        gj.start();

    }

    public int getMapId() {
        return mapId;
    }

    public void setMapId(int mapId) {
        this.mapId = mapId;
    }
}
