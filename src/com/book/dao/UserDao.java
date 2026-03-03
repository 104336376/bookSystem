package com.book.dao;

import com.book.pojo.User;

import java.util.HashMap;
import java.util.Map;

public class UserDao {
    public static Map<String, User> SYSTEM_ADMINS = new HashMap<>();//系统管理员
    public static Map<String, User> BOOK_ADMINS = new HashMap<>();//图书管理员
    public static Map<String, User> BORROWERS = new HashMap<>();//借阅者

    static {
        //管理员的集合
        User admin1 = new User("1","admin1", "1");
        User admin2 = new User("2","admin2", "2");
        SYSTEM_ADMINS.put(admin1.getUserName(), admin1);
        SYSTEM_ADMINS.put(admin2.getUserName(), admin2);

        //图书管理员的集合
        User bookAdmin1 = new User("0","bookAdmin1", "0");
        User bookAdmin2 = new User("1","bookAdmin2", "1");
        BOOK_ADMINS.put(bookAdmin1.getUserName(), bookAdmin1);
        BOOK_ADMINS.put(bookAdmin2.getUserName(), bookAdmin2);



    }
}
