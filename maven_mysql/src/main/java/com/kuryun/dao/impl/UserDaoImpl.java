package com.kuryun.dao.impl;

import com.kuryun.dao.UserDao;
import com.kuryun.domain.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    public List<User> findAll() throws Exception {
        List<User> list = new ArrayList<User>();
        Connection connection = null;
        //获取操作数据的对象
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //获取连接
            connection = DriverManager.getConnection("jdbc:mysql:///test");
            //获取操作数据的对象
            pst = connection.prepareCall("select *  from user"); //prepareCall可以执行存储过程和sql
            //pst = connection.prepareStatement("select *  from user"); //prepareStatement只能执行sql
            //执行
            rs = pst.executeQuery();
            while (rs.next()){
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                list.add(user);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(connection != null){
                connection.close();
            }
            if(pst != null){
                pst.close();
            }
            if(rs != null){
                rs.close();
            }
        }
        return list;
    }
}
