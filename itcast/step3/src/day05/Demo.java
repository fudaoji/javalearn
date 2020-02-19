package day05;

import org.junit.Test;
import utils.JDBCUtilV1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Demo {
    public static void main(String[] args) {

    }

    /**
     * jdbc的入门介绍
     *
     * @throws Exception
     */
    public static void getStart() throws Exception {

        //1、导入jdbc的jar包
        //2、注册驱动
        Class.forName("com.mysql.jdbc.Driver");
        //3、获取数据库连接对象
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "123456");

        //4、定义SQL语句
        String sql = "UPDATE user SET name='user4'";

        //5、获取执行sql语句的对象Statement
        Statement stmt = conn.createStatement();
        //6、执行sql语句
        int count = stmt.executeUpdate(sql);
        //7、处理结果
        System.out.println("此次执行影响结果：" + count + "行");

        //8、释放资源
        stmt.close();
        conn.close();
    }

    @Test
    public void updateOne() {
        Connection conn = null;
        Statement stmt = null;

        try {
            conn = JDBCUtilV1.getConnection();
            stmt = conn.createStatement();

            String sql = "update user set name='user3' where id=3";
            int count = stmt.executeUpdate(sql);
            System.out.println(count);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtilV1.close(stmt, conn);
        }
    }

    @Test
    public void addOne() {
        Connection conn = null;
        Statement stmt = null;

        try {
            conn = JDBCUtilV1.getConnection();
            stmt = conn.createStatement();

            String sql = "insert into user(name) values('user4')";
            int count = stmt.executeUpdate(sql);
            System.out.println(count);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtilV1.close(stmt, conn);
        }
    }

    @Test
    public void findAll() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtilV1.getConnection();
            stmt = conn.createStatement();

            String sql = "SELECT *  FROM user";
            rs = stmt.executeQuery(sql);
            List<User> list = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                User user = new User(id, name);
                list.add(user);
            }
            System.out.println(list);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtilV1.close(rs, stmt, conn);
        }

    }
}
