package com.coderalliance.apimaster.model.vo.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CreateUserReq {
    @JsonProperty("user_name")
    private String userName;
    @JsonProperty("user_password")
    private String userPassword;
    @JsonProperty("user_email")
    private String userEmail;
}
