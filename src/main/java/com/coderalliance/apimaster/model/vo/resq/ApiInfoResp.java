package com.coderalliance.apimaster.model.vo.resq;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class ApiInfoResp {
    @JsonProperty("api_id")
    private Long id;
    @JsonProperty("project_id")
    private Long projectId;
    private String description;
    private String protocol;
    private String path;
    private String method;
    @JsonProperty("header_params")
    private Object headerParams;
    @JsonProperty("query_params")
    private Object queryParams;
    @JsonProperty("body_params")
    private String bodyParams;
    @JsonProperty("response_body")
    private String response;
}
