package edu.hstc.roast.service;

import edu.hstc.roast.dao.UserMapper;
import edu.hstc.roast.module.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public User checkLoginAndPwd(User user) {
        return userMapper.checkLoginAndPwd(user);
    }

    @Override
    public int addUser(User user) {
        return userMapper.addUser(user);
    }

    @Override
    public List<User> queryAllUsers() {
        return userMapper.queryAllUsers();
    }

    @Override
    public User queryUserByUserID(int id) {
        return userMapper.queryUserByUserID(id);
    }

    @Override
    public User queryUserByUserUUID(String uuid) {
        return userMapper.queryUserByUserUUID(uuid);
    }

    @Override
    public User queryUserByUserIDWithPostingList(int id) {
        return userMapper.queryUserByUserIDWithPostingList(id);
    }

    @Override
    public List<User> queryUsersByName(User user) {
        return userMapper.queryUsersByName(user);
    }

    @Override
    public User queryUserByUsername(String username) {
        return userMapper.queryUserByUsername(username);
    }

    @Override
    public int updateUserByID(User user) {
        return userMapper.updateUserByID(user);
    }

    @Override
    public int deleteUserByID(int id) {
        return userMapper.deleteUserByID(id);
    }

    @Override
    public int deleteUserByUUID(String uuid) {
        return userMapper.deleteUserByUUID(uuid);
    }
}
