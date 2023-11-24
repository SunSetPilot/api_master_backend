package com.coderalliance.apimaster.model.vo.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class BatchImportApiReq {
    @JsonProperty("project_id")
    @NotNull(message = "project_id can not be null")
    private Long projectId;

    @JsonProperty("git_address")
    private String gitAddress;

    @JsonProperty("git_branch")
    private String gitBranch;

    @JsonProperty("auto_import_api")
    private Boolean autoImport;
}
