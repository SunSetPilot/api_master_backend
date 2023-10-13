package com.coderalliance.apimaster.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class SendMsgUtil {

    public static void sendMessage(HttpServletResponse response, String str) throws Exception {
        response.setContentType("text/html; charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.print(str);
        writer.close();
        response.flushBuffer();
    }

    public static void sendJsonMessage(HttpServletResponse response, Object obj) throws Exception {
        response.setContentType("application/json; charset=utf-8");
        PrintWriter writer = response.getWriter();
        ObjectMapper objectMapper = new ObjectMapper();
        String objectToJson = objectMapper.writeValueAsString(obj);
        writer.print(objectToJson);
        writer.close();
        response.flushBuffer();
    }
}