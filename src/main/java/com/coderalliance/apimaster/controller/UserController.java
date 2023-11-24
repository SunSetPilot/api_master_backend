package com.coderalliance.apimaster.controller;

import com.coderalliance.apimaster.constant.StatusCode;
import com.coderalliance.apimaster.exception.BusinessException;
import com.coderalliance.apimaster.exception.PermissionException;
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
        } catch (BusinessException e) {
            return BaseResponse.error(e.getMessage());
        } catch (Exception e) {
            log.error("get user error: ", e);
            return BaseResponse.error("internal server error");
        }
    }

    @PostMapping("/register")
    public BaseResponse<Boolean> createUser(@Validated @RequestBody CreateUserReq req) {
        try {
            userService.createUser(req);
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
            String currentUserEmail = (String) request.getSession().getAttribute("userEmail");
            userService.updateUser(id, req, currentUserEmail);
            if (req.getUserEmail() != null) {
                request.getSession().setAttribute("userEmail", req.getUserEmail());
            }
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
            userService.login(req.getEmail(), req.getPassword());
            request.getSession().setAttribute("userEmail", req.getEmail());
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
            request.getSession().removeAttribute("userEmail");
            return BaseResponse.success();
        } catch (Exception e) {
            log.error("logout error: ", e);
            return BaseResponse.error("internal server error");
        }

    }

    @GetMapping("/all")
    public BaseResponse<List<UserInfoResp>> getAllUser() {
        try {
            return BaseResponse.success(userService.getAllUser());
        } catch (BusinessException e) {
            return BaseResponse.error(e.getMessage());
        }
        catch (Exception e) {
            log.error("get all user error: ", e);
            return BaseResponse.error("internal server error");
        }
    }
}
