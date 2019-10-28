package edu.hstc.roast.dao;

import edu.hstc.roast.module.User;

import java.util.List;

public interface UserMapper {
    User checkLoginAndPwd(User user);
    int addUser(User user);
    List<User> queryAllUsers();
    User queryUserByUserID(int id);
    User queryUserByUserUUID(String uuid);
    User queryUserByUsername(String username);
    User queryUserByUserIDWithPostingList(int id);
    List<User> queryUsersByName(User user);
    int updateUserByID(User user);
    int deleteUserByID(int id);
    int deleteUserByUUID(String uuid);
}
