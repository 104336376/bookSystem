package com.book.frame.book;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.book.dao.BookDao;
import com.book.dao.BorrowedBookDAO;
import com.book.dao.BorrowerDao;
import com.book.util.SystemConstants;
import com.book.frame.book.BookAdminPanel;

public class BookManagePanel extends JInternalFrame {

    public BookManagePanel(String bookNo) {
        super("图书借阅", true, true, true, true);
        setSize(SystemConstants.FRAME_WIDTH, SystemConstants.FRAME_HEIGHT);
        setLayout(new BorderLayout());  // 明确布局

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        this.setContentPane(panel);
        Box boxBase = Box.createHorizontalBox();
        Box boxLeft = Box.createVerticalBox();
        boxLeft.add(new JLabel("工号"));
        boxLeft.add(Box.createVerticalStrut(8));
        boxLeft.add(new JLabel("图书编号"));
        boxLeft.add(Box.createVerticalStrut(8));
        Box boxRight = Box.createVerticalBox();
        JTextField field1 = new JTextField(10);
        boxRight.add(field1);
        boxRight.add(Box.createVerticalStrut(5));
        JTextField field2 = new JTextField(10);
        boxRight.add(field2);
        field2.setText(bookNo);
        field2.setEditable(false);
        boxRight.add(Box.createVerticalStrut(5));


        JButton borrowBook = new JButton("借阅");
        borrowBook.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Object[] book = BookDao.findById(bookNo);
                int count = (int) book[5];
                if (count < 1) {
                    //getParent()返回包含这个按钮的上一层容器
                    //JOptionPane.showMessageDialog(父组件, 消息内容);
                    //父组件：指定对话框属于哪个窗口（例如按钮 getParent() 或 this），决定对话框显示位置。如果设为 null，则显示在屏幕中央。
                    JOptionPane.showMessageDialog(borrowBook.getParent(), "库存不足", "系统提示", JOptionPane.WARNING_MESSAGE);
                    BookAdminPanel.setContent(new BookTablePanel());
                    return;
                }
                Object[] user = BorrowerDao.findById(field1.getText());
                if (user[0] == null) {
                    JOptionPane.showMessageDialog(borrowBook.getParent(), "用户不存在", "系统提示", JOptionPane.WARNING_MESSAGE);
                    BookAdminPanel.setContent(new BookTablePanel());
                    return;
                }
                //成功则减库存
                JOptionPane.showMessageDialog( borrowBook.getParent(),"借阅成功", "系统提示", JOptionPane.INFORMATION_MESSAGE);
                BookDao.update(bookNo, -1);
                //返回列表页
                BookAdminPanel.setContent(new BookTablePanel());
                BorrowedBookDAO.add(field1.getText(), book);
            }
        });

        JButton returnBook = new JButton("归还");
        returnBook.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int result = BorrowedBookDAO.update(field1.getText(), field2.getText());
                if (result > 0) {
                    BookDao.update(bookNo, 1);
                    JOptionPane.showMessageDialog(returnBook.getParent(), "归还成功", "系统提示", JOptionPane.WARNING_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(returnBook.getParent(), "用户没有借阅过", "系统提示", JOptionPane.WARNING_MESSAGE);
                }
                BookAdminPanel.setContent(new BookTablePanel());
            }
        });

        boxRight.add(borrowBook);
        boxRight.add(returnBook);
        boxBase.add(boxLeft);
        boxBase.add(Box.createHorizontalStrut(8));
        boxBase.add(boxRight);
        panel.add(boxBase);

    }
}
