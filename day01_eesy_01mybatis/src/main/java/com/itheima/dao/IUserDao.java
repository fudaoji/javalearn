package com.itheima.dao;

import com.itheima.domain.User;

import java.util.List;

/**
 * @author 黑马程序员
 * @Company http://www.ithiema.com
 *
 * 用户的持久层接口
 */
public interface IUserDao {

    /**
     * 查询所有操作
     * @return
     */
    List<User> findAll();

    /**
     * 保存用户信息
     * @param user
     */
    void save(User user);

    /**
     * 更新用户
     * @param user
     */
    void update(User user);

    /**
     * 删除用户
     * @param userId
     */
    void delete(Integer userId);

    /**
     * 根据id获取单个用户
     * @param userId
     * @return
     */
    User findOne(Integer userId);
}
