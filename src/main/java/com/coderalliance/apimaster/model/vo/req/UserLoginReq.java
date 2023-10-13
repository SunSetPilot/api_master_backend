package com.coderalliance.apimaster.model.vo.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserLoginReq {
    @JsonProperty("user_email")
    private String email;
    @JsonProperty("user_password")
    private String password;
}
