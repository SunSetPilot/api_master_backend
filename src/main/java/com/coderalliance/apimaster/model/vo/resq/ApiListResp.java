package com.coderalliance.apimaster.model.vo.resq;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiListResp {
    @JsonProperty("api_id")
    private Long id;
    private String description;
    private String method;
}
