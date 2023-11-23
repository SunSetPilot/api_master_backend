package com.coderalliance.apimaster.model.vo.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
public class CreateApiReq {
    private String description;
    private String protocol;
    private String path;
    private String method;
    @JsonProperty("header_params")
    private Map<String, Object> headerParams;
    @JsonProperty("query_params")
    private Map<String, Object> queryParams;
    @JsonProperty("response_body")
    private String response;
}
