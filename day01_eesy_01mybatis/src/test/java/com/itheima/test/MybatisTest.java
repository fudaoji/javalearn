package com.itheima.test;

import com.itheima.dao.IUserDao;
import com.itheima.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * @author 黑马程序员
 * @Company http://www.ithiema.com
 * mybatis的入门案例
 */
public class MybatisTest {
    InputStream is;
    SqlSession sqlSession;
    IUserDao userDao;

    /**
     * 测试前置方法
     * @throws IOException
     */
    @Before
    public void init() throws IOException {
        //1.读取配置文件
        is = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建SqlSessionFactory工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(is);
        //3.使用工厂生产SqlSession对象
        sqlSession = factory.openSession();
        //4.使用SqlSession创建Dao接口的代理对象
        userDao = sqlSession.getMapper(IUserDao.class);
    }

    /**
     * 测试后置方法
     */
    @After
    public void destroy() throws IOException {
        sqlSession.commit(); //提交事务

        //6.释放资源
        sqlSession.close();
        is.close();
    }

    /**
     * 入门案例
     */
    @Test
    public void findAll() {
        //5.使用代理对象执行方法
        List<User> users = userDao.findAll();
        for(User user : users){
            System.out.println(user);
        }
    }

    /**
     * 新增用户
     */
    @Test
    public void save(){
        User user = new User();
        user.setUsername("用户" + (int) (Math.random() * 100));
        user.setBirthday(new Date());
        user.setAddress("国贸商务中心");
        user.setSex("男");
        System.out.println("插入前：=================");
        System.out.println(user);
        userDao.save(user);
        System.out.println("插入后：=================");
        System.out.println(user);
    }

    /**
     * 修改用户
     */
    @Test
    public void update(){
        User user = new User();
        user.setId(2);
        user.setUsername("id2被更新了");
        user.setBirthday(new Date());
        user.setAddress("国贸商务中心");
        user.setSex("男");
        userDao.update(user);
    }

    /**
     * 删除用户
     */
    @Test
    public void delete(){
        userDao.delete(7);
    }

    /**
     * 寻找单个用户信息
     */
    @Test
    public void findOne(){
        User user = userDao.findOne(1);
        System.out.println(user);
    }
}
