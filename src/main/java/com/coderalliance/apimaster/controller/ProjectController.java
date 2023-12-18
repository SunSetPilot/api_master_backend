package com.coderalliance.apimaster.controller;

import com.coderalliance.apimaster.constant.StatusCode;
import com.coderalliance.apimaster.exception.BusinessException;
import com.coderalliance.apimaster.exception.PermissionException;
import com.coderalliance.apimaster.model.entity.Project;
import com.coderalliance.apimaster.model.vo.BaseResponse;
import com.coderalliance.apimaster.model.vo.req.CreateProjectReq;
import com.coderalliance.apimaster.model.vo.resq.ProjectInfoResp;
import com.coderalliance.apimaster.model.vo.resq.ProjectListResp;
import com.coderalliance.apimaster.model.vo.resq.UserInfoResp;
import com.coderalliance.apimaster.service.ApiService;
import com.coderalliance.apimaster.service.PermissionService;
import com.coderalliance.apimaster.service.ProjectService;
import com.coderalliance.apimaster.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/project")
@Slf4j
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private UserService userService;

    @Autowired
    private ApiService apiService;

    @GetMapping("")
    public BaseResponse<List<ProjectListResp>> getProjectList(HttpServletRequest request) {
        try {
            Long userId = (Long) request.getSession().getAttribute("userId");
            List<Long> projectIds = permissionService.getUserProjectIds(userId);
            List<ProjectListResp> projectListResp = projectService.getProjectListByIds(projectIds).stream().map(
                    project -> ProjectListResp.builder()
                            .id(project.getId())
                            .name(project.getName())
                            .apiCount(apiService.countApi(project.getId()))
                            .owner(userService.getUserById(project.getOwnerId()).getName())
                            .build()
            ).collect(Collectors.toList());
            return BaseResponse.success(projectListResp);
        } catch (BusinessException e) {
            return BaseResponse.error(e.getMessage());
        } catch (Exception e) {
            log.error("get project list error: ", e);
            return BaseResponse.error("internal server error");
        }
    }

    @GetMapping("/{id}/members")
    public BaseResponse<List<UserInfoResp>> getProjectMembers(@PathVariable Long id, HttpServletRequest request) {
        try {
            Long userId = (Long) request.getSession().getAttribute("userId");
            permissionService.checkProjectPermission(id, userId);
            List<Long> userIds = permissionService.getProjectMemberIds(id);
            List<UserInfoResp> userInfoResp = userService.getUserByIds(userIds).stream().map(
                    user -> UserInfoResp.builder()
                            .id(user.getId())
                            .name(user.getName())
                            .email(user.getEmail())
                            .build()
            ).collect(Collectors.toList());
            return BaseResponse.success(userInfoResp);
        } catch (PermissionException e) {
            return BaseResponse.error(StatusCode.FORBIDDEN, e.getMessage());
        } catch (BusinessException e) {
            return BaseResponse.error(e.getMessage());
        } catch (Exception e) {
            log.error("get project members error: ", e);
            return BaseResponse.error("internal server error");
        }
    }

    @GetMapping("/{id}")
    public BaseResponse<ProjectInfoResp> getProject(@PathVariable Long id, HttpServletRequest request) {
        try {
            Long userId = (Long) request.getSession().getAttribute("userId");
            permissionService.checkProjectPermission(id, userId);
            Project project = projectService.getProjectById(id);
            ProjectInfoResp projectInfoResp = ProjectInfoResp.builder()
                    .id(project.getId())
                    .name(project.getName())
                    .apiCount(apiService.countApi(project.getId()))
                    .owner(userService.getUserById(project.getOwnerId()).getName())
                    .members(permissionService.getProjectMemberIds(project.getId()))
                    .autoImport(project.getAutoImport())
                    .gitAddress(project.getGitAddress())
                    .gitBranch(project.getGitBranch())
                    .build();
            return BaseResponse.success(projectInfoResp);
        } catch (PermissionException e) {
            return BaseResponse.error(StatusCode.FORBIDDEN, e.getMessage());
        } catch (BusinessException e) {
            return BaseResponse.error(e.getMessage());
        } catch (Exception e) {
            log.error("get project error: ", e);
            return BaseResponse.error("internal server error");
        }
    }

    @PostMapping("")
    public BaseResponse<Boolean> createProject(@Validated @RequestBody CreateProjectReq req, HttpServletRequest request) {
        try {
            Long userId = (Long) request.getSession().getAttribute("userId");
            projectService.createProject(req, userId);
            return BaseResponse.success();
        } catch (BusinessException e) {
            return BaseResponse.error(e.getMessage());
        } catch (Exception e) {
            log.error("create project error: ", e);
            return BaseResponse.error("internal server error");
        }
    }

    @PutMapping("/{id}")
    public BaseResponse<Boolean> updateProject(@PathVariable Long id, @Validated @RequestBody CreateProjectReq req, HttpServletRequest request) {
        try {
            Long userId = (Long) request.getSession().getAttribute("userId");
            permissionService.checkProjectPermission(id, userId);
            projectService.updateProject(id, req);
            return BaseResponse.success();
        } catch (PermissionException e) {
            return BaseResponse.error(StatusCode.FORBIDDEN, e.getMessage());
        } catch (BusinessException e) {
            return BaseResponse.error(e.getMessage());
        } catch (Exception e) {
            log.error("update project error: ", e);
            return BaseResponse.error("internal server error");
        }
    }

    @DeleteMapping("/{id}")
    public BaseResponse<Boolean> deleteProject(@PathVariable Long id, HttpServletRequest request) {
        try {
            Long userId = (Long) request.getSession().getAttribute("userId");
            projectService.deleteProject(id, userId);
            return BaseResponse.success();
        } catch (PermissionException e) {
            return BaseResponse.error(StatusCode.FORBIDDEN, e.getMessage());
        } catch (BusinessException e) {
            return BaseResponse.error(e.getMessage());
        } catch (Exception e) {
            log.error("delete project error: ", e);
            return BaseResponse.error("internal server error");
        }
    }
}
