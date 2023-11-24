package com.coderalliance.apimaster.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.coderalliance.apimaster.dao.UserMapper;
import com.coderalliance.apimaster.exception.BusinessException;
import com.coderalliance.apimaster.exception.PermissionException;
import com.coderalliance.apimaster.model.entity.User;
import com.coderalliance.apimaster.model.vo.req.CreateUserReq;
import com.coderalliance.apimaster.model.vo.resq.UserInfoResp;
import com.coderalliance.apimaster.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Long login(String email, String password) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(User::getEmail, email);
        User user = userMapper.selectOne(queryWrapper);
        if (user == null || !user.getPassword().equals(DigestUtils.md5DigestAsHex(password.getBytes()))) {
            throw new PermissionException("wrong email or password");
        }
        return user.getId();
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
            throw new BusinessException("user not exist");
        } else {
            return UserInfoResp.builder()
                    .id(user.getId())
                    .name(user.getName())
                    .email(user.getEmail())
                    .build();
        }
    }

    @Override
    public List<UserInfoResp> getUserByIds(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return Collections.emptyList();
        }
        List<User> users = userMapper.selectBatchIds(ids);
        return users.stream().map(user -> UserInfoResp.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build()).collect(Collectors.toList());
    }

    @Override
    public Long createUser(CreateUserReq req) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(User::getEmail, req.getUserEmail());
        User existUser = userMapper.selectOne(queryWrapper);
        if (existUser != null) {
            throw new BusinessException("user already exist");
        } else {
            User newUser = User.builder()
                    .name(req.getUserName())
                    .email(req.getUserEmail())
                    .password(DigestUtils.md5DigestAsHex(req.getUserPassword().getBytes()))
                    .build();
            userMapper.insert(newUser);
            return newUser.getId();
        }
    }

    @Override
    public void updateUser(Long id, CreateUserReq req, Long currentUserId) {
        if (currentUserId.equals(id)) {
            User user = User.builder()
                    .id(id)
                    .name(req.getUserName())
                    .email(req.getUserEmail())
                    .password(DigestUtils.md5DigestAsHex(req.getUserPassword().getBytes()))
                    .build();
            userMapper.updateById(user);
        } else {
            throw new PermissionException("can not update other user");
        }
    }
}
