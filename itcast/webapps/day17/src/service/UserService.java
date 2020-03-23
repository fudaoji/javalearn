package service;

import domain.PageBean;
import domain.User;

import java.util.Map;

public interface UserService {
    /**
     * @param loginUser
     * @return
     */
    public User login(User loginUser);

    PageBean<User> findUserByPage(String currentPage, String rows, Map<String, String[]> condition);

    int addUser(User user);

    User findUser(int id);

    int updateUser(User user);

    int delUser(String id);
}
