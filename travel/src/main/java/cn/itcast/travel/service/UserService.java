package cn.itcast.travel.service;

import cn.itcast.travel.domain.User;

public interface UserService {
    public boolean register(User user);

    boolean active(String code);

    public User login(User user);
}
