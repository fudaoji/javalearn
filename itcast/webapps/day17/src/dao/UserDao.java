package dao;

import domain.User;

import java.util.List;
import java.util.Map;

public interface UserDao {
    public User login(User loginUser);

    int findTotalCount(Map<String, String[]> condition);

    List<User> findByPage(int start, int rows, Map<String, String[]> condition);

    int add(User user);

    User findOne(int id);

    int updateOne(User user);

    int delete(int id);
}
