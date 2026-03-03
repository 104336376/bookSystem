package com.book.frame.system;

import com.book.frame.LoginPanel;
import com.book.frame.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import com.book.util.SystemConstants;

public class SystemAdminPanel extends JPanel{
    private static JDesktopPane contentPanel = new JDesktopPane();
    public SystemAdminPanel() {

        this.setBounds(0, 0, SystemConstants.FRAME_WIDTH, SystemConstants.FRAME_HEIGHT);
        contentPanel.setBounds(0, 20, SystemConstants.FRAME_WIDTH, SystemConstants.FRAME_HEIGHT - 50);
        this.add(contentPanel, BorderLayout.CENTER);//将内部窗口添加到窗体中间
        JMenuBar menuBar = new JMenuBar();//创建菜单栏的容器
        JMenu parentMenu = new JMenu("借阅者管理");//一级菜单1
        JMenu SystemMenu = new JMenu("退出登录");//一级菜单2
        menuBar.add(parentMenu);//将一级菜单1放入容器
        menuBar.add(SystemMenu);

        parentMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setContent(new SystemTablePanel());
            }
        });

        SystemMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                MainFrame.setContent(new LoginPanel());
            }
        });

        this.setLayout(new BorderLayout());
        menuBar.setBounds(0, 0, SystemConstants.FRAME_WIDTH, 50);
        this.add(menuBar, BorderLayout.NORTH);
    }

    public static void setContent(JInternalFrame internalFrame) {
        internalFrame.setSize(SystemConstants.FRAME_WIDTH - 100, SystemConstants.FRAME_HEIGHT - 100);
        //显示内部窗口
        internalFrame.setVisible(true);
        contentPanel.removeAll();//重新绘制窗口
        contentPanel.repaint();
        contentPanel.add(internalFrame);
    }
}
