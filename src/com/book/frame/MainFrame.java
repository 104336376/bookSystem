package com.book.frame;

import com.book.pojo.User;

import javax.swing.*;
import com.book.util.SystemConstants;

import java.awt.*;

public class MainFrame {
    //项目主入口
    //主窗体对象，声明为 public static 以便全局访问
    public static final JFrame frame = new JFrame("图书系统--(作者徐浩敏)");
    public static User user;//当前登录用户信息，静态变量，全局可访问

    //程序入口方法,初始化主窗口并显示登录面板
    public static void main(String[] args) {
        // 设置窗体大小（从 SystemContents 常量类读取，便于统一修改）
        frame.setSize(SystemConstants.FRAME_WIDTH, SystemConstants.FRAME_HEIGHT);
        // 设置窗口居中显示（必须在 setSize 之后调用，否则以默认大小居中）
        frame.setLocationRelativeTo(null);
        // 设置默认关闭操作：点击窗口关闭按钮时退出程序
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 设置布局管理器为 null，采用绝对定位）
        frame.setLayout(null);


        // 创建登录面板实例（LoginPanel 继承自 JPanel，包含了登录界面的所有组件）
        LoginPanel loginPanel = new LoginPanel();
        // 将登录面板设置为主窗口的内容面板
        frame.setContentPane(loginPanel);

        frame.setVisible(true); // 最后显示窗口



    }


    //用于动态切换主窗口的内容 例如登录成功后切换到主界面面板
    public static void setContent(JPanel panel) {
        // 将当前窗口的内容面板替换为传入的 panel
        frame.setContentPane(panel);//自动切换
        // 重要：切换内容面板后必须调用 revalidate() 和 repaint() 来刷新窗口
        // 否则新面板可能无法正确显示或布局错乱
        frame.revalidate();
        frame.repaint();
    }

}
