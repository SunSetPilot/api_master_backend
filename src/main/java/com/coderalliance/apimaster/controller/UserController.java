package com.coderalliance.apimaster.controller;

import com.coderalliance.apimaster.constant.StatusCode;
import com.coderalliance.apimaster.exception.BusinessException;
import com.coderalliance.apimaster.exception.PermissionException;
import com.coderalliance.apimaster.model.entity.User;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public BaseResponse<UserInfoResp> getUser(@PathVariable Long id) {
        try {
            User user = userService.getUserById(id);
            UserInfoResp userInfoResp = UserInfoResp.builder()
                    .id(id)
                    .name(user.getName())
                    .email(user.getEmail())
                    .build();
            return BaseResponse.success(userInfoResp);
        } catch (BusinessException e) {
            return BaseResponse.error(e.getMessage());
        } catch (Exception e) {
            log.error("get user error: ", e);
            return BaseResponse.error("internal server error");
        }
    }

    @PostMapping("/register")
    public BaseResponse<Boolean> createUser(@Validated @RequestBody CreateUserReq req, HttpServletRequest request) {
        try {
            Long userId = userService.createUser(req);
            request.getSession().setAttribute("userId", userId);
            return BaseResponse.success();
        } catch (BusinessException e) {
            return BaseResponse.error(e.getMessage());
        } catch (Exception e) {
            log.error("create user error:", e);
            return BaseResponse.error("internal server error");
        }
    }

    @PutMapping("/{id}")
    public BaseResponse<Boolean> updateUser(@PathVariable Long id, @Validated @RequestBody CreateUserReq req, HttpServletRequest request) {
        try {
            Long currentUserId = (Long) request.getSession().getAttribute("userId");
            userService.updateUser(id, req, currentUserId);
            return BaseResponse.success();
        } catch (PermissionException e) {
            return BaseResponse.error(StatusCode.FORBIDDEN, e.getMessage());
        } catch (BusinessException e) {
            return BaseResponse.error(e.getMessage());
        } catch (Exception e) {
            log.error("update user error:", e);
            return BaseResponse.error("internal server error");
        }
    }

    @PostMapping("/login")
    public BaseResponse<Boolean> login(@Validated @RequestBody UserLoginReq req, HttpServletRequest request) {
        try {
            Long userId = userService.login(req.getEmail(), req.getPassword());
            request.getSession().setAttribute("userId", userId);
            return BaseResponse.success();
        } catch (PermissionException e) {
            return BaseResponse.error(StatusCode.FORBIDDEN, e.getMessage());
        } catch (BusinessException e) {
            return BaseResponse.error(e.getMessage());
        } catch (Exception e) {
            log.error("login error: ", e);
            return BaseResponse.error("internal server error");
        }
    }

    @GetMapping("/logout")
    public BaseResponse<Boolean> logout(HttpServletRequest request) {
        try {
            request.getSession().removeAttribute("userId");
            return BaseResponse.success();
        } catch (Exception e) {
            log.error("logout error: ", e);
            return BaseResponse.error("internal server error");
        }

    }

    @GetMapping("/all")
    public BaseResponse<List<UserInfoResp>> getAllUser() {
        try {
            List<UserInfoResp> userInfoResp = userService.getAllUser().stream().map(
                    user -> UserInfoResp.builder()
                            .id(user.getId())
                            .name(user.getName())
                            .email(user.getEmail())
                            .build())
                    .collect(Collectors.toList());
            return BaseResponse.success(userInfoResp);
        } catch (BusinessException e) {
            return BaseResponse.error(e.getMessage());
        }
        catch (Exception e) {
            log.error("get all user error: ", e);
            return BaseResponse.error("internal server error");
        }
    }
}
