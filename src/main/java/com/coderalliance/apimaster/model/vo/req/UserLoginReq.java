package com.coderalliance.apimaster.model.vo.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class UserLoginReq {

    @Email(message = "please input a valid email")
    @JsonProperty("user_email")
    private String email;

    @NotEmpty(message = "user password can not be empty")
    @JsonProperty("user_password")
    private String password;
}
