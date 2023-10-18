package com.coderalliance.apimaster.service;

import com.coderalliance.apimaster.model.vo.req.CreateUserReq;
import com.coderalliance.apimaster.model.vo.resq.UserInfoResp;

import java.util.List;

public interface UserService {
    Boolean login(String email, String password);
    List<UserInfoResp> getAllUser();
    UserInfoResp getUserById(Long id);
    Boolean createUser(CreateUserReq req);
    Boolean updateUser(Long id, CreateUserReq req, String currentUserEmail);
}
