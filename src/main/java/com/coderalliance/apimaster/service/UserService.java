package com.coderalliance.apimaster.service;

import com.coderalliance.apimaster.model.vo.req.CreateUserReq;
import com.coderalliance.apimaster.model.vo.resq.UserInfoResp;

import java.util.List;

public interface UserService {
    Long login(String email, String password);
    List<UserInfoResp> getAllUser();
    UserInfoResp getUserById(Long id);
    Long createUser(CreateUserReq req);
    void updateUser(Long id, CreateUserReq req, Long currentUserId);
}
