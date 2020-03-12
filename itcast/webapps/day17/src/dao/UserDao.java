package dao;

import domain.User;

import java.util.List;

/**
 * User 操作类
 */
public interface UserDao {

    public List<User> findAll();
}
