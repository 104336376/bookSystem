package com.book.dao;

import java.util.ArrayList;
import java.util.List;

public class BookDao {
    //表格列名
    public static final Object[] columnNames = {"编号", "书名", "出版社", "出版年份", "价格", "数量"};
    public static final List<Object[]> data = new ArrayList<>();

    static {
        add(new Object[]{"1", "《算法导论》", " cambridge university press", 2000, 10.0, 10});
        add(new Object[]{"2", "《数据结构》", " cambridge university press", 2001, 11.0, 12});
        add(new Object[]{"3", "《C++ Primer》", " cambridge university press", 2002, 20.0, 1});
    }

    //Jtable需要转换为二维数组
    public static Object[][] toList(List<Object[]> list) {
        Object[][] result = new Object[list.size()][];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    public static Object[] findById(String id) {
        //遍历当前集合
        for (Object[] d : data) {
            //如果id相等,返回该对象
            if (d[0].equals(id)) {
                return d;
            }
        }
        //找不到返回一个空的初始化对象 空数组
        return new Object[columnNames.length];
    }

    public static void add(Object[] obj) {
        data.add(obj);
    }

    public static void update(String id, int amount) {
        //遍历集合,查询到与输入的id相同的对象
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i)[0].equals(id)) {
                //进行数据更新
                int count = (int) (data.get(i)[5]);
                data.get(i)[5] = count + amount;
                break;
            }
        }
    }

    public static void remove(String id) {
        //遍历集合,查询到与输入的id相同的对象
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i)[0].equals(id)) {
                //进行数据删除
                data.remove(i);
                break;
            }
        }
    }

}
