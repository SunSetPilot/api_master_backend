package com.coderalliance.apimaster.controller;

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

    @GetMapping("/{project_id}")
    public BaseResponse<List<ApiListResp>> getApiList(@PathVariable Long project_id) {
        try {
            return BaseResponse.success(apiService.getApiList(project_id));
        } catch (Exception e) {
            log.error("get api list error", e);
            return BaseResponse.error("get api list error: " + e.getMessage());
        }
    }

    @PostMapping("/")
    public BaseResponse<Boolean> createApi(@Validated @RequestBody CreateApiReq req) {
        try {
            Boolean success = apiService.createApi(req);
            if (!success) {
                return BaseResponse.error("create api error!");
            } else {
                return BaseResponse.success();
            }
        } catch (Exception e) {
            log.error("create api error", e);
            return BaseResponse.error("create api error: " + e.getMessage());
        }
    }

    @PutMapping("/{api_id}")
    public BaseResponse<Boolean> updateApi(@PathVariable Long api_id, @Validated @RequestBody CreateApiReq req) {
        try {
            Boolean success = apiService.updateApi(api_id, req);
            if (!success) {
                return BaseResponse.error("update api error!");
            } else {
                return BaseResponse.success();
            }
        } catch (Exception e) {
            log.error("update api error", e);
            return BaseResponse.error("update api error: " + e.getMessage());
        }
    }

    @DeleteMapping("/{api_id}")
    public BaseResponse<Boolean> deleteApi(@PathVariable Long api_id) {
        try {
            Boolean success = apiService.deleteApi(api_id);
            if (!success) {
                return BaseResponse.error("delete api error!");
            } else {
                return BaseResponse.success();
            }
        } catch (Exception e) {
            log.error("delete api error", e);
            return BaseResponse.error("delete api error: " + e.getMessage());
        }
    }

    @PostMapping("/import")
    public BaseResponse<Boolean> batchImportApi(@Validated @RequestBody BatchImportApiReq req) {
        try {
            Boolean success = apiService.batchImportApi(req);
            if (!success) {
                return BaseResponse.error("import api error!");
            } else {
                return BaseResponse.success();
            }
        } catch (Exception e) {
            log.error("import api error", e);
            return BaseResponse.error("import api error: " + e.getMessage());
        }
    }
}
