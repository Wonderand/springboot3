package top.hu.test.spring6_test.service;


import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//@Component
public class GoodsServiceImpl {

    public void get(){
//        try {
//            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","123");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        System.out.println("方法执行成功>>>");
    }
}
