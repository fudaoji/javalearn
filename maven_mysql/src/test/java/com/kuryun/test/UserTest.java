package com.kuryun.test;

import com.kuryun.dao.impl.UserDaoImpl;
import com.kuryun.domain.User;
import org.junit.Test;

import java.util.List;

public class UserTest {

    @Test
    public void findAll() throws Exception {
        UserDaoImpl userDao = new UserDaoImpl();
        List<User> list = userDao.findAll();
        for (User user : list) {
            System.out.println(user);
        }
    }
}
