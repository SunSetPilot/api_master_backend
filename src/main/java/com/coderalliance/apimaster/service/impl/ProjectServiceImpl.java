package com.coderalliance.apimaster.service.impl;

import com.coderalliance.apimaster.dao.ProjectMapper;
import com.coderalliance.apimaster.exception.BusinessException;
import com.coderalliance.apimaster.exception.PermissionException;
import com.coderalliance.apimaster.model.entity.Project;
import com.coderalliance.apimaster.model.vo.req.CreateProjectReq;
import com.coderalliance.apimaster.service.ApiService;
import com.coderalliance.apimaster.service.PermissionService;
import com.coderalliance.apimaster.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private ApiService apiService;

    @Autowired
    private PermissionService permissionService;

    @Override
    public List<Project> getProjectListByIds(List<Long> projectIds) {
        if (projectIds == null || projectIds.isEmpty()) {
            return Collections.emptyList();
        }
        return projectMapper.selectBatchIds(projectIds);
    }

    @Override
    public Project getProjectById(Long id) {
        Project project = projectMapper.selectById(id);
        if (project == null) {
            throw new BusinessException("project not exist");
        }
        return project;
    }

    @Override
    public void createProject(CreateProjectReq req, Long userId) {
        Project project = Project.builder()
                .name(req.getName())
                .ownerId(userId)
                .autoImport(req.getAutoImport())
                .gitAddress(req.getGitAddress())
                .gitBranch(req.getGitBranch())
                .build();
        projectMapper.insert(project);
        if (!req.getMembers().contains(userId)) {
           req.getMembers().add(userId);
        }
        permissionService.updateProjectMembers(project.getId(), req.getMembers());
    }

    @Override
    public void updateProject(Long id, CreateProjectReq req) {
        Project oldProject = projectMapper.selectById(id);
        if (oldProject == null) {
            throw new BusinessException("project not exist");
        }
        Long ownerId = oldProject.getOwnerId();
        Project project = Project.builder()
                .id(id)
                .name(req.getName())
                .ownerId(ownerId)
                .autoImport(req.getAutoImport())
                .gitAddress(req.getGitAddress())
                .gitBranch(req.getGitBranch())
                .build();
        projectMapper.updateById(project);
        if (!req.getMembers().contains(ownerId)) {
            req.getMembers().add(ownerId);
        }
        permissionService.updateProjectMembers(project.getId(), req.getMembers());
    }

    @Override
    public void deleteProject(Long id, Long userId) {
        Project project = projectMapper.selectById(id);
        if (project == null) {
            throw new BusinessException("project not exist");
        }
        if (!project.getOwnerId().equals(userId)) {
            throw new PermissionException("you have no permission to delete this project");
        }
        projectMapper.deleteById(id);
        apiService.deleteApiByProjectId(id);
        permissionService.deleteProjectPermission(id);
    }

    @Override
    public void setAutoImport(Long id, Boolean autoImport, String gitAddress, String gitBranch) {
        Project project = projectMapper.selectById(id);
        if (project == null) {
            throw new BusinessException("project not exist");
        }
        Project newProject = Project.builder()
                .id(id)
                .name(project.getName())
                .ownerId(project.getOwnerId())
                .autoImport(autoImport)
                .gitAddress(gitAddress)
                .gitBranch(gitBranch)
                .build();
        projectMapper.updateById(newProject);
    }
}
