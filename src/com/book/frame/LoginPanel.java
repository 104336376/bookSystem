package com.book.frame;

import com.book.dao.UserDao;
import com.book.frame.book.BookAdminPanel;
import com.book.frame.borrow.BorrowerPanel;
import com.book.frame.system.SystemAdminPanel;
import com.book.pojo.User;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.book.util.SystemConstants;

public class LoginPanel extends JPanel {
    public LoginPanel() {
        //因为主窗口用了绝对布局（ setLayout(null) ），所以面板必须自己设置位置和大小
        //否则不会显示。这里设置从 (0,0) 开始，宽高与主窗口一致，这样面板就填满了整个窗口。
        this.setBounds(0, 0, SystemConstants.FRAME_WIDTH, SystemConstants.FRAME_HEIGHT);
        //添加输入框
        this.add(new JLabel("用户名"));
        JTextField nameField = new JTextField(10);
        this.add(nameField);

        this.add(new JLabel("密码"));
        JPasswordField passwordField = new JPasswordField(10);
        this.add(passwordField);

        //如果做权限划分(管理)就加这个身份按钮 不需要可注释
        //权限按钮开始
        JRadioButton systemRadio = new JRadioButton("系统管理员",true);
        JRadioButton bookRadio = new JRadioButton("图书管理员");
        JRadioButton borrowRadio = new JRadioButton("借阅者");
        ButtonGroup group = new ButtonGroup();//创建按钮组
        group.add(systemRadio);// 将两个单选按钮放入同一个 ButtonGroup，确保它们互斥（只能选一个)
        group.add(bookRadio);
        group.add(borrowRadio);
        //将单选按钮加入面板
        this.add(systemRadio);
        this.add(bookRadio);
        this.add(borrowRadio);
        //权限按钮结束
        JButton loginButton = new JButton("登录");
        this.add(loginButton);

        //登录按钮点击事件(使用 MouseAdapter 只需重写 mouseClicked）
        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //获取用户输入的用户名和密码
                String userName = nameField.getText();
                //JPasswordField 的getPassword()方法默认返回的是 char[]，出于安全考虑，使用时再转成 String
                //(因为字符数组用完后可以立即清空，而字符串会一直留在内存中。这里我们把它转成字符串方便比较)
                String password = new String(passwordField.getPassword());

                // 临时变量：用于存储根据用户名查找到的用户对象
                User user = null;
                // 根据身份选择决定要切换的面板
                JPanel panel = null;
                //isSelected()判断单选按钮被选中
                if (systemRadio.isSelected()) {
                    // 从数据源（UserData.ADMIN_USERS）中根据用户名获取用户对象
                    user = UserDao.SYSTEM_ADMINS.get(userName);
                    // 登录成功后跳转
                    panel = new SystemAdminPanel();
                } else if (bookRadio.isSelected()) {
                    user = UserDao.BOOK_ADMINS.get(userName);
                    panel = new BookAdminPanel();
                } else if (borrowRadio.isSelected()) {
                    user = UserDao.BORROWERS.get(userName);
                    panel = new BorrowerPanel();
                }
                if (user == null || !user.getPassword().equals(password)) {
                    // 如果验证失败，弹出错误提示对话框
                    // LoginPanel.this 表示当前登录面板对象，作为对话框的父组件
                    JOptionPane.showMessageDialog(LoginPanel.this, "用户名或密码错误", "系统提示", JOptionPane.ERROR_MESSAGE);
                } else {
                    // 验证成功：切换主窗口的内容面板为目标面板（SystemAdminPanel 或 UserPanel）
                    MainFrame.setContent(panel);
                    // 将当前登录的用户信息保存到全局变量 MainFrame.user 中，供其他界面使用
                    MainFrame.user = user;
                }
            }
        });


    }

}
