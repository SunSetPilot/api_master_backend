package com.coderalliance.apimaster.model.vo.resq;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProjectListResp {
    @JsonProperty("project_id")
    private Long id;
    @JsonProperty("project_name")
    private String name;
    @JsonProperty("api_count")
    private Long apiCount;
    @JsonProperty("project_owner")
    private String owner;
}
