package com.coderalliance.apimaster.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@TableName("user")
public class User {
    private Long id;
    private String name;
    private String password;
    private String email;
}
