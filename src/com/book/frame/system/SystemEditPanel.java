package com.book.frame.system;

import com.book.dao.BorrowerDao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;

import com.book.frame.book.BookAdminPanel;
import com.book.util.SystemConstants;

public class SystemEditPanel extends JInternalFrame {

    public SystemEditPanel() {
        super("编辑数据", true, true, true, true);
        this.setSize(SystemConstants.FRAME_WIDTH - 20, SystemConstants.FRAME_HEIGHT - 50);
        //创建内容面板
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        //设置内部窗口的内容面板
        this.setContentPane(panel);
        Box boxBase = Box.createHorizontalBox();//水平布局的容器
        Box boxLeft = Box.createVerticalBox();
        boxLeft.add(new JLabel("工号"));
        boxLeft.add(Box.createVerticalStrut(8));
        boxLeft.add(new JLabel("姓名"));
        boxLeft.add(Box.createVerticalStrut(8));
        boxLeft.add(new JLabel("性别"));
        boxLeft.add(Box.createVerticalStrut(8));
        boxLeft.add(new JLabel("入职年份"));
        boxLeft.add(Box.createVerticalStrut(8));
        boxLeft.add(new JLabel("用户名"));
        boxLeft.add(Box.createVerticalStrut(8));
        boxLeft.add(new JLabel("密码"));
        boxLeft.add(Box.createVerticalStrut(30));
        Box boxRight = Box.createVerticalBox();
        JTextField field1 = new JTextField(10);
        boxRight.add(field1);
        boxRight.add(Box.createVerticalStrut(5));
        JTextField field2 = new JTextField(10);
        boxRight.add(field2);
        boxRight.add(Box.createVerticalStrut(5));

        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.addItem("男");
        comboBox.addItem("女");
        boxRight.add(comboBox);
        boxRight.add(Box.createVerticalStrut(5));
        JTextField field4 = new JTextField(10);
        boxRight.add(field4);
        boxRight.add(Box.createVerticalStrut(5));
        JTextField field5 = new JTextField(10);
        boxRight.add(field5);
        boxRight.add(Box.createVerticalStrut(5));
        JTextField field6 = new JTextField(10);
        boxRight.add(field6);
        boxRight.add(Box.createVerticalStrut(5));


        JButton btn = new JButton("提交");
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                //点击提交时,可以获取到当前ID以及每一个文本框的值
                //根据Data类的顺序,添加到数组中
                Object[] data = new Object[]{field1.getText(), field2.getText(), comboBox.getSelectedItem()
                , field4.getText(), field5.getText(), field6.getText()};
                BorrowerDao.add(data);
                SystemAdminPanel.setContent(new SystemTablePanel());
            }
        });
        boxRight.add(btn);
        boxBase.add(boxLeft);
        boxBase.add(Box.createHorizontalStrut(8));
        boxBase.add(boxRight);
        panel.add(boxBase);

        //显示内部窗口
        this.setVisible(true);

    }
}
