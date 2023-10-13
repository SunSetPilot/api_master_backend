package com.coderalliance.apimaster.model.vo.resq;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserInfoResp {
    @JsonProperty("user_id")
    private Long id;
    @JsonProperty("user_name")
    private String name;
    @JsonProperty("user_email")
    private String email;
}
