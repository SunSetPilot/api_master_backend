package com.coderalliance.apimaster.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
@TableName(value = "api", autoResultMap = true)
public class Api {
    @TableId(type = IdType.AUTO)
    private Long id;
    @TableField("project_id")
    private Long projectId;
    private String description;
    private String method;
    private String path;
    private String protocol;
    @TableField(value = "header_params", typeHandler = JacksonTypeHandler.class)
    private Map<String, Object> headerParams;
    @TableField(value = "query_params", typeHandler = JacksonTypeHandler.class)
    private Map<String, Object> queryParams;
    @TableField(value = "body_params", typeHandler = JacksonTypeHandler.class)
    private Map<String, Object> bodyParams;
}
