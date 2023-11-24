package com.coderalliance.apimaster.service;

import com.coderalliance.apimaster.model.vo.req.BatchImportApiReq;
import com.coderalliance.apimaster.model.vo.req.CreateApiReq;
import com.coderalliance.apimaster.model.vo.resq.ApiListResp;

import java.util.List;

public interface ApiService {
    List<ApiListResp> getApiList(Long projectId);

    void createApi(Long project_id, CreateApiReq req);

    void updateApi(Long apiId, CreateApiReq req);

    void deleteApi(Long apiId);

    void batchImportApi(BatchImportApiReq req);
}
