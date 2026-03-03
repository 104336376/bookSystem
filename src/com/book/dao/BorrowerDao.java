package com.book.dao;

import com.book.pojo.User;

import java.util.ArrayList;
import java.util.List;

public class BorrowerDao {
    public static final Object[] columnNames = {"工号", "姓名", "性别", "入职年份", "用户名", "密码"};
    public static final List<Object[]> data = new ArrayList<>();


    static {
        add(new Object[]{"0", "张三", "男", "2015", "z", "0"});
        add(new Object[]{"1", "李四", "女", "2016", "l", "1"});

    }

    //需要转换为二维数组
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
            //如果id相等,返回该对象数组
            if (d[0].equals(id)) {
                return d;
            }
        }
        //找不到返回一个空的初始化对象
        return new Object[columnNames.length];
    }

    //添加新的借阅者
    public static void add(Object[] obj) {
        data.add(obj);
        UserDao.BORROWERS.put((String) obj[4], new User((String) obj[0],
                (String) obj[4], (String) obj[5]));
    }

    public static void update(int id, Object[] obj) {
        //遍历集合,查询到与输入的id相同的对象
        for (int i = 0; i < data.size(); i++) {
            if ((int) data.get(i)[0] == id) {
                //进行数据更新
                data.set(i, obj);
                break;
            }
        }
    }

    public static void remove(String id) {
        //遍历集合,查询到与输入的id相同的对象
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i)[0].equals(String.valueOf(id))){
                //进行数据删除
                data.remove(i);
                break;
            }
        }
    }
}
