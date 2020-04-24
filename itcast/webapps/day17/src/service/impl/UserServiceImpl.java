package service.impl;

import dao.impl.UserDaoImpl;
import domain.User;
import service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    /**
     * 获取所有用户数据
     * @return
     */
    public List<User> findAll(){
        UserDaoImpl userDao = new UserDaoImpl();
        return  userDao.findAll();
    }

    /**
     * 登录
     * @param loginUser
     * @return
     */
    @Override
    public User login(User loginUser) {
        return  new UserDaoImpl().login(loginUser);
    }
}
