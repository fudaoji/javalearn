package cn.itcast.travel;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.dao.impl.UserDaoImpl;
import cn.itcast.travel.domain.User;
import org.junit.Test;

public class UserDaoTest {
    private UserDao userDao = new UserDaoImpl();

    @Test
    public void TestFindByUsername(){
        String username = "admin";
        User user = userDao.findByUsername(username);
        System.out.println(user);
    }
}
