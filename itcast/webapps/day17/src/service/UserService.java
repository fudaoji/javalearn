package service;

import domain.PageBean;
import domain.User;

import java.util.Map;

public interface UserService {
<<<<<<< HEAD
    public List<User> findAll();
    public User login(User loginUser);
=======
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
>>>>>>> 0a5cb22bc0b4e113362f2e3b8a910d07bbbbd3df
}
