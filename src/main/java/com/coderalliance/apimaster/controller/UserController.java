package com.coderalliance.apimaster.controller;

import com.coderalliance.apimaster.constant.StatusCode;
import com.coderalliance.apimaster.model.vo.BaseResponse;
import com.coderalliance.apimaster.model.vo.req.CreateUserReq;
import com.coderalliance.apimaster.model.vo.req.UserLoginReq;
import com.coderalliance.apimaster.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public BaseResponse getUser(@PathVariable String id) {
        try {
            return BaseResponse.success(userService.getUserById(Long.valueOf(id)));
        } catch (Exception e) {
            log.error("get user error", e);
            return BaseResponse.error("get user error: " + e.getMessage());
        }
    }

    @PostMapping("")
    public BaseResponse createUser(@RequestBody CreateUserReq req) {
        try {
            userService.createUser(req);
            return BaseResponse.success();
        } catch (Exception e) {
            log.error("create user error", e);
            return BaseResponse.error("create user error: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public BaseResponse updateUser(@PathVariable String id, @RequestBody CreateUserReq req) {
        try {
            userService.updateUser(Long.valueOf(id), req);
            return BaseResponse.success();
        } catch (Exception e) {
            log.error("update user error", e);
            return BaseResponse.error("update user error: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public BaseResponse login(@RequestBody UserLoginReq req, HttpServletRequest request) {
        try {
            Boolean loginSuccess = userService.login(req.getEmail(), req.getPassword());
            if (loginSuccess) {
                request.getSession().setAttribute("userEmail", req.getEmail());
                return BaseResponse.success();
            } else {
                return BaseResponse.error(StatusCode.FORBIDDEN, "username or password error!");
            }
        } catch (Exception e) {
            log.error("login error", e);
            return BaseResponse.error("login error: " + e.getMessage());
        }

    }

    @GetMapping("/logout")
    public BaseResponse logout(HttpServletRequest request) {
        try {
            request.getSession().removeAttribute("userEmail");
            return BaseResponse.success();
        } catch (Exception e) {
            log.error("logout error", e);
            return BaseResponse.error("logout error: " + e.getMessage());
        }

    }

    @GetMapping("/all")
    public BaseResponse getAllUser() {
        try {
            return BaseResponse.success(userService.getAllUser());
        } catch (Exception e) {
            log.error("get all user error", e);
            return BaseResponse.error("get all user error: " + e.getMessage());
        }
    }
}
