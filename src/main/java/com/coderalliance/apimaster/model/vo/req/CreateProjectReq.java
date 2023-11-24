package com.coderalliance.apimaster.model.vo.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Size;
import java.util.List;

@Data
public class CreateProjectReq {
    @JsonProperty("project_name")
    @Size(min = 1, max = 255, message = "project name length should be 1-255")
    private String name;
    private List<Long> members;
    @JsonProperty("auto_import_api")
    private Boolean autoImport;
    @JsonProperty("git_address")
    private String gitAddress;
    @JsonProperty("git_branch")
    private String gitBranch;
}
