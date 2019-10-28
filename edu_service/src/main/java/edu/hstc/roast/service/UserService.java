package edu.hstc.roast.service;

import edu.hstc.roast.module.User;

import java.util.List;

public interface UserService {
    User checkLoginAndPwd(User user);
    int addUser(User user);
    List<User> queryAllUsers();
    User queryUserByUserID(int id);
    User queryUserByUserUUID(String uuid);
    User queryUserByUserIDWithPostingList(int id);
    List<User> queryUsersByName(User user);
    User queryUserByUsername(String username);
    int updateUserByID(User user);
    int deleteUserByID(int id);
    int deleteUserByUUID(String uuid);
}
