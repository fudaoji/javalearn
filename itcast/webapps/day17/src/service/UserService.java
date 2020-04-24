package service;

import domain.User;

import java.util.List;

public interface UserService {
    public List<User> findAll();
    public User login(User loginUser);
}
