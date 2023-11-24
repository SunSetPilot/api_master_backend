package com.coderalliance.apimaster.service;

import com.coderalliance.apimaster.model.vo.req.CreateProjectReq;
import com.coderalliance.apimaster.model.vo.resq.ProjectInfoResp;
import com.coderalliance.apimaster.model.vo.resq.ProjectListResp;

import java.util.List;

public interface ProjectService {
    List<ProjectListResp> getProjectListByIds(List<Long> projectIds);

    ProjectInfoResp getProjectById(Long id);

    void createProject(CreateProjectReq req, Long userId);

    void updateProject(Long id, CreateProjectReq req);

    void deleteProject(Long id, Long userId);
}
