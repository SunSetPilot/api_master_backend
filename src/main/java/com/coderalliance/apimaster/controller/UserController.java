package com.coderalliance.apimaster.controller;

import com.coderalliance.apimaster.constant.StatusCode;
import com.coderalliance.apimaster.model.vo.BaseResponse;
import com.coderalliance.apimaster.model.vo.req.CreateUserReq;
import com.coderalliance.apimaster.model.vo.req.UserLoginReq;
import com.coderalliance.apimaster.model.vo.resq.UserInfoResp;
import com.coderalliance.apimaster.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public BaseResponse<UserInfoResp> getUser(@PathVariable Long id) {
        try {
            return BaseResponse.success(userService.getUserById(id));
        } catch (Exception e) {
            log.error("get user error", e);
            return BaseResponse.error("get user error: " + e.getMessage());
        }
    }

    @PostMapping("/register")
    public BaseResponse<Boolean> createUser(@Validated @RequestBody CreateUserReq req) {
        try {
            Boolean success = userService.createUser(req);
            if (!success) {
                return BaseResponse.error("user already exist!");
            } else {
                return BaseResponse.success();
            }
        } catch (Exception e) {
            log.error("create user error", e);
            return BaseResponse.error("create user error: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public BaseResponse<Boolean> updateUser(@PathVariable Long id, @Validated @RequestBody CreateUserReq req, HttpServletRequest request) {
        try {
            String currentUserEmail = (String) request.getSession().getAttribute("userEmail");
            Boolean success = userService.updateUser(id, req, currentUserEmail);
            if (success) {
                if (req.getUserEmail() != null) {
                    request.getSession().setAttribute("userEmail", req.getUserEmail());
                }
                return BaseResponse.success();
            } else {
                return BaseResponse.error(StatusCode.FORBIDDEN, "can not update other user!");
            }
        } catch (Exception e) {
            log.error("update user error", e);
            return BaseResponse.error("update user error: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public BaseResponse<Boolean> login(@Validated @RequestBody UserLoginReq req, HttpServletRequest request) {
        try {
            Boolean loginSuccess = userService.login(req.getEmail(), req.getPassword());
            if (loginSuccess) {
                request.getSession().setAttribute("userEmail", req.getEmail());
                return BaseResponse.success();
            } else {
                return BaseResponse.error(StatusCode.FORBIDDEN, "wrong email or password!");
            }
        } catch (Exception e) {
            log.error("login error", e);
            return BaseResponse.error("login error: " + e.getMessage());
        }
    }

    @GetMapping("/logout")
    public BaseResponse<Boolean> logout(HttpServletRequest request) {
        try {
            request.getSession().removeAttribute("userEmail");
            return BaseResponse.success();
        } catch (Exception e) {
            log.error("logout error", e);
            return BaseResponse.error("logout error: " + e.getMessage());
        }

    }

    @GetMapping("/all")
    public BaseResponse<List<UserInfoResp>> getAllUser() {
        try {
            return BaseResponse.success(userService.getAllUser());
        } catch (Exception e) {
            log.error("get all user error", e);
            return BaseResponse.error("get all user error: " + e.getMessage());
        }
    }
}
