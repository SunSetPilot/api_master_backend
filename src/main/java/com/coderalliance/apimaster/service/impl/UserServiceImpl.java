package com.coderalliance.apimaster.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.coderalliance.apimaster.dao.UserMapper;
import com.coderalliance.apimaster.model.entity.User;
import com.coderalliance.apimaster.model.vo.req.CreateUserReq;
import com.coderalliance.apimaster.model.vo.resq.UserInfoResp;
import com.coderalliance.apimaster.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public Boolean login(String email, String password) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(User::getEmail, email);
        User user = userMapper.selectOne(queryWrapper);
        if (user == null) {
            return false;
        } else {
            String passwordMd5 = DigestUtils.md5DigestAsHex(password.getBytes());
            return user.getPassword().equals(passwordMd5);
        }
    }

    @Override
    public List<UserInfoResp> getAllUser() {
        List<User> users = userMapper.selectList(null);
        return users.stream().map(user -> UserInfoResp.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build()).collect(Collectors.toList());
    }

    @Override
    public UserInfoResp getUserById(Long id) {
        User user = userMapper.selectById(id);
        if (user == null) {
            return null;
        } else {
            return UserInfoResp.builder()
                    .id(user.getId())
                    .name(user.getName())
                    .email(user.getEmail())
                    .build();
        }
    }

    @Override
    public Boolean createUser(CreateUserReq req) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(User::getEmail, req.getUserEmail());
        User existUser = userMapper.selectOne(queryWrapper);
        if (existUser != null) {
            return false;
        } else {
            User newUser = User.builder()
                    .name(req.getUserName())
                    .email(req.getUserEmail())
                    .password(DigestUtils.md5DigestAsHex(req.getUserPassword().getBytes()))
                    .build();
            userMapper.insert(newUser);
            return true;
        }
    }

    @Override
    public Boolean updateUser(Long id, CreateUserReq req, String currentUserEmail) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(User::getEmail, currentUserEmail);
        User currentUser = userMapper.selectOne(queryWrapper);
        if (currentUser != null && currentUser.getId().equals(id)) {
            User user = User.builder()
                    .id(id)
                    .name(req.getUserName())
                    .email(req.getUserEmail())
                    .password(DigestUtils.md5DigestAsHex(req.getUserPassword().getBytes()))
                    .build();
            userMapper.updateById(user);
            return true;
        } else {
            return false;
        }
    }
}
