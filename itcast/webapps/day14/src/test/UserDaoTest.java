package test;

import dao.UserDao;
import domain.User;
import org.junit.Test;

public class UserDaoTest {

    /**
     * 登陆测试
     */
    @Test
    public void loginTest(){
        User user = new User();
        user.setName("user0");
        user.setPassword("123456");

        UserDao userDao = new UserDao();
        User res = userDao.login(user);
        System.out.println(res);
    }
}
