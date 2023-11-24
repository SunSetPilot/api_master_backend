package com.coderalliance.apimaster.controller;

import com.coderalliance.apimaster.exception.BusinessException;
import com.coderalliance.apimaster.model.vo.BaseResponse;
import com.coderalliance.apimaster.model.vo.req.BatchImportApiReq;
import com.coderalliance.apimaster.model.vo.req.CreateApiReq;
import com.coderalliance.apimaster.model.vo.resq.ApiListResp;
import com.coderalliance.apimaster.service.ApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apis")
@Slf4j
public class ApiController {

    @Autowired
    private ApiService apiService;

    // todo check permission
    @GetMapping("/{project_id}")
    public BaseResponse<List<ApiListResp>> getApiList(@PathVariable Long project_id) {
        try {
            return BaseResponse.success(apiService.getApiList(project_id));
        } catch (BusinessException e) {
            return BaseResponse.error(e.getMessage());
        } catch (Exception e) {
            log.error("get api list error: ", e);
            return BaseResponse.error("internal server error");
        }
    }

    @PostMapping("/{project_id}")
    public BaseResponse<Boolean> createApi(@PathVariable Long project_id, @Validated @RequestBody CreateApiReq req) {
        try {
            apiService.createApi(project_id, req);
            return BaseResponse.success();
        } catch (BusinessException e) {
            return BaseResponse.error(e.getMessage());
        } catch (Exception e) {
            log.error("create api error: ", e);
            return BaseResponse.error("internal server error");
        }
    }

    @PutMapping("/{api_id}")
    public BaseResponse<Boolean> updateApi(@PathVariable Long api_id, @Validated @RequestBody CreateApiReq req) {
        try {
            apiService.updateApi(api_id, req);
            return BaseResponse.success();
        } catch (BusinessException e) {
            return BaseResponse.error(e.getMessage());
        }catch (Exception e) {
            log.error("update api error: ", e);
            return BaseResponse.error("internal server error");
        }
    }

    @DeleteMapping("/{api_id}")
    public BaseResponse<Boolean> deleteApi(@PathVariable Long api_id) {
        try {
            apiService.deleteApi(api_id);
            return BaseResponse.success();
        } catch (BusinessException e) {
            return BaseResponse.error(e.getMessage());
        } catch (Exception e) {
            log.error("delete api error: ", e);
            return BaseResponse.error("internal server error");
        }
    }

    @PostMapping("/import")
    public BaseResponse<Boolean> batchImportApi(@Validated @RequestBody BatchImportApiReq req) {
        try {
            apiService.batchImportApi(req);
            return BaseResponse.success();
        } catch (BusinessException e) {
            return BaseResponse.error(e.getMessage());
        } catch (Exception e) {
            log.error("batch import api error: ", e);
            return BaseResponse.error("internal server error");
        }
    }
}
