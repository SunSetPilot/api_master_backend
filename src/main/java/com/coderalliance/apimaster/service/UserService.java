package com.coderalliance.apimaster.service;

import com.coderalliance.apimaster.model.entity.User;
import com.coderalliance.apimaster.model.vo.req.CreateUserReq;

import java.util.List;

public interface UserService {
    Long login(String email, String password);
    List<User> getAllUser();
    User getUserById(Long id);
    List<User> getUserByIds(List<Long> ids);
    Long createUser(CreateUserReq req);
    void updateUser(Long id, CreateUserReq req, Long currentUserId);
}
