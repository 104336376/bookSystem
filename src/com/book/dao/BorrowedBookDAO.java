package com.book.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BorrowedBookDAO {
    public static final Object[] columnNames = {"编号", "书名", "出版社", "出版年份", "价格"};
    //books集合用于存放用户的借阅记录,String为用户编号,list为用户借阅的图书集合
    public static Map<String, List<Object[]>> BOOKS = new HashMap<>();



    public static Object[][] toList(String user) {
        List<Object[]> list = BOOKS.get(user);
        if(list != null){
            //需要转换为table控件可以识别的二维数组
            Object[][] result = new Object[list.size()][];
            for (int i = 0; i < list.size(); i++) {
                result[i] = list.get(i);
            }
            return result;
        }
        return new Object[0][columnNames.length];
    }
    //int传递列索引，String传递搜索内容

    //string代表用户编号,book代表图书对象
    public static int add(String user, Object[] book) {
        //从借阅的集合中获取用户编号查询
        List<Object[]> books = BOOKS.get(user);
        //如果为空则代表没有借阅记录,需要初始化list集合放入借阅集合
        if (books == null) {
            books = new ArrayList<>();
            BOOKS.put(user, books);
        }
        //将当前图书对象放入list集合
        books.add(book);
        //代表借阅成功
        return 1;
    }
    public static int update(String user, String bookNo) {
        List<Object[]> books = BOOKS.get(user);
        if(books == null){
            return 0;
         }
        for(Object[] book:books){
            if(book[0].equals(bookNo)){
                books.remove(book);
                return 1;
            }
        }
        return 0;
    }
}
