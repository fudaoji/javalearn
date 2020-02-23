package test;

import dao.UserDao;
import domain.User;
import org.junit.Test;

public class UserDaoTest {
    @Test
    public void loginTest() {
        User loginUser = new User();
        loginUser.setUsername("dashen");
        loginUser.setPassword("123456");

        UserDao userDao = new UserDao();
        User user = userDao.login(loginUser);
        System.out.println(user);
    }
}
