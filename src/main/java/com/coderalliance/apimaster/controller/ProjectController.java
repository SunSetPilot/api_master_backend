package com.coderalliance.apimaster.controller;

import com.coderalliance.apimaster.constant.StatusCode;
import com.coderalliance.apimaster.exception.BusinessException;
import com.coderalliance.apimaster.exception.PermissionException;
import com.coderalliance.apimaster.model.vo.BaseResponse;
import com.coderalliance.apimaster.model.vo.req.CreateProjectReq;
import com.coderalliance.apimaster.model.vo.resq.ProjectInfoResp;
import com.coderalliance.apimaster.model.vo.resq.ProjectListResp;
import com.coderalliance.apimaster.model.vo.resq.UserInfoResp;
import com.coderalliance.apimaster.service.PermissionService;
import com.coderalliance.apimaster.service.ProjectService;
import com.coderalliance.apimaster.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/projects")
@Slf4j
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private UserService userService;

    @GetMapping("")
    public BaseResponse<List<ProjectListResp>> getProjectList(HttpServletRequest request) {
        try {
            Long userId = (Long) request.getSession().getAttribute("userId");
            List<Long> projectIds = permissionService.getUserProjectIds(userId);
            return BaseResponse.success(projectService.getProjectListByIds(projectIds));
        } catch (BusinessException e) {
            return BaseResponse.error(e.getMessage());
        } catch (Exception e) {
            log.error("get project list error: ", e);
            return BaseResponse.error("internal server error");
        }
    }

    @GetMapping("/{id}/users")
    public BaseResponse<List<UserInfoResp>> getProjectMembers(@PathVariable Long id, HttpServletRequest request) {
        try {
            Long userId = (Long) request.getSession().getAttribute("userId");
            permissionService.checkProjectPermission(id, userId);
            List<Long> userIds = permissionService.getProjectMemberIds(id);
            return BaseResponse.success(userService.getUserByIds(userIds));
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
            return BaseResponse.success(projectService.getProjectById(id));
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
