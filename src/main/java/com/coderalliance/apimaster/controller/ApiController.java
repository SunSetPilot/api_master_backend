package com.coderalliance.apimaster.controller;

import com.coderalliance.apimaster.constant.StatusCode;
import com.coderalliance.apimaster.exception.BusinessException;
import com.coderalliance.apimaster.exception.PermissionException;
import com.coderalliance.apimaster.model.entity.Api;
import com.coderalliance.apimaster.model.vo.BaseResponse;
import com.coderalliance.apimaster.model.vo.req.BatchImportApiReq;
import com.coderalliance.apimaster.model.vo.req.CreateApiReq;
import com.coderalliance.apimaster.model.vo.resq.ApiInfoResp;
import com.coderalliance.apimaster.model.vo.resq.ApiListResp;
import com.coderalliance.apimaster.model.vo.resq.CreateApiResp;
import com.coderalliance.apimaster.service.ApiService;
import com.coderalliance.apimaster.service.PermissionService;
import com.coderalliance.apimaster.service.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@Slf4j
public class ApiController {

    @Autowired
    private ApiService apiService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private ProjectService projectService;

    @GetMapping("/project/{projectId}")
    public BaseResponse<List<ApiListResp>> getApiList(@PathVariable Long projectId, HttpServletRequest request) {
        try {
            Long currentUserId = (Long) request.getSession().getAttribute("userId");
            permissionService.checkProjectPermission(projectId, currentUserId);
            List<ApiListResp> apiList = apiService.getApiList(projectId).stream().map(
                    api -> ApiListResp.builder()
                            .id(api.getId())
                            .description(api.getDescription())
                            .method(api.getMethod())
                            .build())
                    .collect(Collectors.toList());
            return BaseResponse.success(apiList);
        } catch (PermissionException e){
            return BaseResponse.error(StatusCode.FORBIDDEN, e.getMessage());
        }catch (BusinessException e) {
            return BaseResponse.error(e.getMessage());
        } catch (Exception e) {
            log.error("get api list error: ", e);
            return BaseResponse.error("internal server error");
        }
    }

    @GetMapping("/{apiId}")
    public BaseResponse<ApiInfoResp> getApi(@PathVariable Long apiId, HttpServletRequest request) {
        try {
            Long currentUserId = (Long) request.getSession().getAttribute("userId");
            Api api = apiService.getApi(apiId);
            permissionService.checkProjectPermission(api.getProjectId(), currentUserId);
            ApiInfoResp apiInfoResp = ApiInfoResp.builder()
                    .id(api.getId())
                    .projectId(api.getProjectId())
                    .description(api.getDescription())
                    .protocol(api.getProtocol())
                    .path(api.getPath())
                    .method(api.getMethod())
                    .headerParams(api.getHeaderParams())
                    .queryParams(api.getQueryParams())
                    .bodyParams(api.getBodyParams())
                    .response(api.getResponse())
                    .build();
            return BaseResponse.success(apiInfoResp);
        } catch (PermissionException e){
            return BaseResponse.error(StatusCode.FORBIDDEN, e.getMessage());
        }catch (BusinessException e) {
            return BaseResponse.error(e.getMessage());
        } catch (Exception e) {
            log.error("get api error: ", e);
            return BaseResponse.error("internal server error");
        }
    }

    @PostMapping("")
    public BaseResponse<CreateApiResp> createApi(@Validated @RequestBody CreateApiReq req, HttpServletRequest request) {
        try {
            Long currentUserId = (Long) request.getSession().getAttribute("userId");
            Long projectId = req.getProjectId();
            if (projectId == null) {
                throw new BusinessException("project id can not be null");
            }
            permissionService.checkProjectPermission(req.getProjectId(), currentUserId);
            Long id = apiService.createApi(projectId, req);
            return BaseResponse.success(CreateApiResp.builder().id(id).build());
        } catch (PermissionException e){
            return BaseResponse.error(StatusCode.FORBIDDEN, e.getMessage());
        } catch (BusinessException e) {
            return BaseResponse.error(e.getMessage());
        } catch (Exception e) {
            log.error("create api error: ", e);
            return BaseResponse.error("internal server error");
        }
    }

    @PutMapping("/{apiId}")
    public BaseResponse<Boolean> updateApi(@PathVariable Long apiId, @Validated @RequestBody CreateApiReq req, HttpServletRequest request) {
        try {
            Long currentUserId = (Long) request.getSession().getAttribute("userId");
            Api oldApi = apiService.getApi(apiId);
            permissionService.checkProjectPermission(oldApi.getProjectId(), currentUserId);
            apiService.updateApi(oldApi, req);
            return BaseResponse.success();
        } catch (PermissionException e){
            return BaseResponse.error(StatusCode.FORBIDDEN, e.getMessage());
        } catch (BusinessException e) {
            return BaseResponse.error(e.getMessage());
        }catch (Exception e) {
            log.error("update api error: ", e);
            return BaseResponse.error("internal server error");
        }
    }

    @DeleteMapping("/{apiId}")
    public BaseResponse<Boolean> deleteApi(@PathVariable Long apiId, HttpServletRequest request) {
        try {
            Long currentUserId = (Long) request.getSession().getAttribute("userId");
            Api oldApi = apiService.getApi(apiId);
            permissionService.checkProjectPermission(oldApi.getProjectId(), currentUserId);
            apiService.deleteApi(oldApi);
            return BaseResponse.success();
        } catch (PermissionException e){
            return BaseResponse.error(StatusCode.FORBIDDEN, e.getMessage());
        } catch (BusinessException e) {
            return BaseResponse.error(e.getMessage());
        } catch (Exception e) {
            log.error("delete api error: ", e);
            return BaseResponse.error("internal server error");
        }
    }

    @PostMapping("/import")
    public BaseResponse<Boolean> batchImportApi(@Validated @RequestBody BatchImportApiReq req, HttpServletRequest request) {
        try {
            Long currentUserId = (Long) request.getSession().getAttribute("userId");
            permissionService.checkProjectPermission(req.getProjectId(), currentUserId);
            projectService.setAutoImport(req.getProjectId(), req.getAutoImport(), req.getGitAddress(), req.getGitBranch());
            apiService.batchImportApi(req);
            return BaseResponse.success();
        } catch (PermissionException e){
            return BaseResponse.error(StatusCode.FORBIDDEN, e.getMessage());
        } catch (BusinessException e) {
            return BaseResponse.error(e.getMessage());
        } catch (Exception e) {
            log.error("batch import api error: ", e);
            return BaseResponse.error("internal server error");
        }
    }
}
