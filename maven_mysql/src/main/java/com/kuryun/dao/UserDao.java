package com.kuryun.dao;

import com.kuryun.domain.User;

import java.util.List;

public interface UserDao {
    public List<User> findAll() throws Exception;
}
