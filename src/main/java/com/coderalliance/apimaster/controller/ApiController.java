package com.coderalliance.apimaster.controller;

import com.coderalliance.apimaster.constant.StatusCode;
import com.coderalliance.apimaster.exception.BusinessException;
import com.coderalliance.apimaster.exception.PermissionException;
import com.coderalliance.apimaster.model.entity.Api;
import com.coderalliance.apimaster.model.vo.BaseResponse;
import com.coderalliance.apimaster.model.vo.req.BatchImportApiReq;
import com.coderalliance.apimaster.model.vo.req.CreateApiReq;
import com.coderalliance.apimaster.model.vo.resq.ApiListResp;
import com.coderalliance.apimaster.service.ApiService;
import com.coderalliance.apimaster.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/apis")
@Slf4j
public class ApiController {

    @Autowired
    private ApiService apiService;

    @Autowired
    private PermissionService permissionService;

    @GetMapping("/{projectId}")
    public BaseResponse<List<ApiListResp>> getApiList(@PathVariable Long projectId, HttpServletRequest request) {
        try {
            Long currentUserId = (Long) request.getSession().getAttribute("userId");
            permissionService.checkProjectPermission(projectId, currentUserId);
            return BaseResponse.success(apiService.getApiList(projectId));
        } catch (PermissionException e){
            return BaseResponse.error(StatusCode.FORBIDDEN, e.getMessage());
        }catch (BusinessException e) {
            return BaseResponse.error(e.getMessage());
        } catch (Exception e) {
            log.error("get api list error: ", e);
            return BaseResponse.error("internal server error");
        }
    }

    @PostMapping("/{projectId}")
    public BaseResponse<Boolean> createApi(@PathVariable Long projectId, @Validated @RequestBody CreateApiReq req, HttpServletRequest request) {
        try {
            Long currentUserId = (Long) request.getSession().getAttribute("userId");
            permissionService.checkProjectPermission(projectId, currentUserId);
            apiService.createApi(projectId, req);
            return BaseResponse.success();
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
