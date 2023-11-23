package com.coderalliance.apimaster.service;

import com.coderalliance.apimaster.model.vo.req.BatchImportApiReq;
import com.coderalliance.apimaster.model.vo.req.CreateApiReq;
import com.coderalliance.apimaster.model.vo.resq.ApiListResp;

import java.util.List;

public interface ApiService {
    List<ApiListResp> getApiList(Long projectId);

    Boolean createApi(CreateApiReq req);

    Boolean updateApi(Long apiId, CreateApiReq req);

    Boolean deleteApi(Long apiId);

    Boolean batchImportApi(BatchImportApiReq req);
}
