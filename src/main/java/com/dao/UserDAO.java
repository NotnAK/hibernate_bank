package com.dao;


import com.entity.User;

import java.util.List;

public interface UserDAO {
    void addUser(User user);
/*    void addUserWithAccounts(User user);*/
    void deleteUser(User user);
    void updateUser(User user);
    List<User> getAllUsers();
    User getUserById(int id);
    void close();
}
