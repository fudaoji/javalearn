package day06;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import day05.User;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import utils.JDBCUtil;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class Demo {

    JdbcTemplate template = new JdbcTemplate(JDBCUtil.getDataSource());

    public static void main(String[] args) {

    }

    @Test
    public void jDBCTemplateRowMap() {
        //define sql
        String sql = "select *  from user";
        //run sql
        List<User> list = template.query(sql, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                return user;
            }
        });

        for (User user : list) {
            System.out.println(user);
        }
    }

    @Test
    public void jDBCTemplateObject() {
        //define sql
        String sql = "select count(id)  from user";
        //run sql
        Long count = template.queryForObject(sql, Long.class);
        System.out.println(count);
    }

    @Test
    public void jDBCTemplateBean() {
        //define sql
        String sql = "select *  from user";
        //run sql
        List<User> list = template.query(sql, new BeanPropertyRowMapper<User>(User.class));
        for (User user : list) {
            System.out.println(user);
        }
    }

    @Test
    public void jDBCTemplateList() {
        //define sql
        String sql = "select *  from user";
        //run sql
        List<Map<String, Object>> list = template.queryForList(sql);
        for (Map<String, Object> stringObjectMap : list) {
            System.out.println(stringObjectMap);
        }
    }

    @Test
    public void jDBCTemplate() {
        //import jars
        JdbcTemplate template = new JdbcTemplate(JDBCUtil.getDataSource());
        //define sql
        String sql = "update user set name='user0' where id = ?";
        //run sql
        int count = template.update(sql, 1);
        System.out.println(count);
    }

    @Test
    public void druid() throws Exception {
        //load config file
        Properties pro = new Properties();
        ClassLoader cll = Demo.class.getClassLoader();
        InputStream is = cll.getResourceAsStream("druid.properties");
        pro.load(is);
        //build dataSource
        DataSource ds = DruidDataSourceFactory.createDataSource(pro);
        //get Connection
        Connection c = ds.getConnection();

        System.out.println(c);
    }

    @Test
    public void c3p0() {
        DataSource ds = new ComboPooledDataSource();
        Connection c = null;
        try {
            c = ds.getConnection();
            System.out.println(c);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (c != null) {
                try {
                    c.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
