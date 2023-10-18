package com.coderalliance.apimaster.model.vo.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class CreateUserReq {
    
    @NotEmpty(message = "user name can not be empty")
    @Size(max = 255, message = "user name length should be less than 255")
    @JsonProperty("user_name")
    private String userName;

    @Size(min = 8, message = "user password length should be longer than 8")
    @JsonProperty("user_password")
    private String userPassword;

    @Email(message = "please input a valid email")
    @JsonProperty("user_email")
    private String userEmail;
}
