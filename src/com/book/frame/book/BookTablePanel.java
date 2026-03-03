package com.book.frame.book;


import com.book.util.SystemConstants;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import com.book.dao.BookDao;


public class BookTablePanel extends JInternalFrame {
    public BookTablePanel() {
        super("数据管理", true, true, true, true);
        setSize(SystemConstants.FRAME_WIDTH, SystemConstants.FRAME_HEIGHT);
        setLayout(new BorderLayout());  // 明确布局

        // 表格（只读）
        JTable table = new JTable() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // 顶部按钮面板（使用 JPanel）

        JPanel topPanel = new JPanel();
        JButton addBtn = new JButton("添加");
        topPanel.add(addBtn);
        JButton BookBorrow = new JButton("借还");
        topPanel.add(BookBorrow);
        JButton deleteBtn = new JButton("删除");
        topPanel.add(deleteBtn);

        addBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                BookAdminPanel.setContent(new BookEditPanel());
            }
        });

        BookBorrow.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int rowNum = table.getSelectedRow();
                System.out.println("选中行是" + rowNum);
                //如果未选中行则返回
                if (rowNum <= -1) {
                    return;
                }
                BookAdminPanel.setContent(new BookManagePanel((String) table.getValueAt(rowNum, 0)));
                //获取选中行的id
                //getValueAt(rowNum, 0)这也是JTable的一个方法,它的作用是获取表格中指定行、指定列的那个单元格里的值。
               //SystemAdminPanel.setContent(new DataEditPanel(table.getValueAt(rowNum, 0)));
            }
        });

        deleteBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //获取选中行
                int rowNum = table.getSelectedRow();
                if (rowNum >= 0) {
                    BookDao.remove((String) table.getValueAt(rowNum, 0));
                    TableModel tableModel = new DefaultTableModel(BookDao.toList(BookDao.data), com.book.dao.BookDao.columnNames);
                    table.setModel(tableModel);
                }

            }
        });

        //TableModel是Swing中表格的数据源接口
        //DefaultTableModel是TableModel  接口的一个现成实现，内部用一个二维数组（或Vector）来存放数据，并实现了基本的增删改查方法。
        //使用它，你只需要提供数据和列名，它就能帮你管理这些数据，并通知表格何时刷新。
        TableModel tableModel = new DefaultTableModel(BookDao.toList(BookDao.data), com.book.dao.BookDao.columnNames);
        table.setModel(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // 将表格放入滚动面板
        JScrollPane scrollPane = new JScrollPane(table);

        // 布局：顶部按钮面板放在 NORTH，表格放在 CENTER
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }
}
