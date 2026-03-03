package com.book.frame.borrow;

import com.book.dao.BorrowedBookDAO;
import com.book.frame.MainFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import com.book.util.SystemConstants;
import java.awt.event.MouseEvent;

public class BorrowerBookTablePanel  extends JInternalFrame {

        public BorrowerBookTablePanel() {
            super("数据管理", true, true, true, true);
            setSize(SystemConstants.FRAME_WIDTH, SystemConstants.FRAME_HEIGHT);
            setLayout(new BorderLayout());  // 明确布局

            // 表格（只读）
            JTable table = new JTable() {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            //TableModel是Swing中表格的数据源接口
            //DefaultTableModel是TableModel  接口的一个现成实现，内部用一个二维数组（或Vector）来存放数据，并实现了基本的增删改查方法。
            //使用它，你只需要提供数据和列名，它就能帮你管理这些数据，并通知表格何时刷新。
            TableModel tableModel = new DefaultTableModel(BorrowedBookDAO.toList(MainFrame.user.getId()), BorrowedBookDAO.columnNames);
            table.setModel(tableModel);
            table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            // 将表格放入滚动面板
            JScrollPane scrollPane = new JScrollPane(table);

            add(scrollPane, BorderLayout.CENTER);
        }
}
