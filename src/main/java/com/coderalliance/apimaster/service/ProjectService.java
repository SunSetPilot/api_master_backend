package com.coderalliance.apimaster.service;

import com.coderalliance.apimaster.model.entity.Project;
import com.coderalliance.apimaster.model.vo.req.CreateProjectReq;

import java.util.List;

public interface ProjectService {
    List<Project> getProjectListByIds(List<Long> projectIds);

    Project getProjectById(Long id);

    void createProject(CreateProjectReq req, Long userId);

    void updateProject(Long id, CreateProjectReq req);

    void deleteProject(Long id, Long userId);
}
