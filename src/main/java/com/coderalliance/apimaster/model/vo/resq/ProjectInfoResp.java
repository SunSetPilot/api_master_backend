package com.coderalliance.apimaster.model.vo.resq;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProjectInfoResp {
    @JsonProperty("project_id")
    private Long id;
    @JsonProperty("project_name")
    private String name;
    @JsonProperty("api_count")
    private Long apiCount;
    @JsonProperty("project_owner")
    private String owner;
    private List<Long> members;
    @JsonProperty("auto_import_api")
    private Boolean autoImport;
    @JsonProperty("git_address")
    private String gitAddress;
    @JsonProperty("git_branch")
    private String gitBranch;
}
