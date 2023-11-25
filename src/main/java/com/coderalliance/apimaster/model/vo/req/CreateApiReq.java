package com.coderalliance.apimaster.model.vo.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class CreateApiReq {
    @JsonProperty("project_id")
    private Long projectId;

    @NotEmpty(message = "api description can not be empty")
    @Size(max = 512, message = "api description length should be less than 512")
    private String description;

    @NotEmpty(message = "api protocol can not be empty")
    private String protocol;

    @NotEmpty(message = "api path can not be empty")
    @Size(max = 255, message = "api path length should be less than 255")
    private String path;

    @NotEmpty(message = "api method can not be empty")
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
