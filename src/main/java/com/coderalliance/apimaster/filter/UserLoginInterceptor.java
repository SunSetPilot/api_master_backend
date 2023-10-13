package com.coderalliance.apimaster.filter;

import com.coderalliance.apimaster.constant.StatusCode;
import com.coderalliance.apimaster.model.vo.BaseResponse;
import com.coderalliance.apimaster.utils.SendMsgUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserLoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        String userEmail = (String) session.getAttribute("userEmail");
        if (userEmail != null) {
            return true;
        }
        SendMsgUtil.sendJsonMessage(response, BaseResponse.error(StatusCode.UNAUTHORIZED, "not login!"));
        return false;
    }
}
