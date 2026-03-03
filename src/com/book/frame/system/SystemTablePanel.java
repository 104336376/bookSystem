package com.book.frame.system;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;



import com.book.dao.BorrowerDao;
import com.book.frame.book.BookAdminPanel;
import com.book.util.SystemConstants;

public class SystemTablePanel extends JInternalFrame {

    public SystemTablePanel() {
        super("借阅者管理", true, true, true, true);
        setSize(SystemConstants.FRAME_WIDTH, SystemConstants.FRAME_HEIGHT);
        setLayout(new BorderLayout());  // 明确布局

        // 表格（只读）
        JTable table = new JTable() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        TableModel tableModel = new DefaultTableModel(BorrowerDao.toList(BorrowerDao.data), BorrowerDao.columnNames);
        table.setModel(tableModel);
        // 顶部按钮面板（使用 JPanel）
        JPanel topPanel = new JPanel();
        JButton addBtn = new JButton("添加");
        topPanel.add(addBtn);
        JButton deleteBtn = new JButton("删除");
        topPanel.add(deleteBtn);

        // 将表格放入滚动面板
        JScrollPane scrollPane = new JScrollPane(table);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setSize(SystemConstants.FRAME_WIDTH, SystemConstants.FRAME_HEIGHT);

        addBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SystemAdminPanel.setContent(new SystemEditPanel());
            }
        });
        deleteBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //获取选中行
                int rowNum = table.getSelectedRow();
                if (rowNum >= 0) {
                    BorrowerDao.remove((String) table.getValueAt(rowNum, 0));
                    TableModel tableModel = new DefaultTableModel(BorrowerDao.toList(BorrowerDao.data), BorrowerDao.columnNames);
                    table.setModel(tableModel);
                }

            }
        });

       //把表头添加到容器顶部(使用普通的中间容器添加表格时,表头和内容需要分开添加)
        panel.add(table.getTableHeader(), BorderLayout.NORTH);
        //单选
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        //把表格内容添加到容器中心
        this.add(topPanel, BorderLayout.NORTH);
        panel.add(table, BorderLayout.CENTER);
        this.add(panel, BorderLayout.CENTER);

        //显示内部窗口
        this.setVisible(true);
    }
}
