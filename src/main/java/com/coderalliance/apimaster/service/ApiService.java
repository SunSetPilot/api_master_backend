package com.coderalliance.apimaster.service;

import com.coderalliance.apimaster.model.entity.Api;
import com.coderalliance.apimaster.model.vo.req.BatchImportApiReq;
import com.coderalliance.apimaster.model.vo.req.CreateApiReq;
import com.coderalliance.apimaster.model.vo.resq.ApiListResp;

import java.util.List;

public interface ApiService {
    List<ApiListResp> getApiList(Long projectId);

    Api getApi(Long apiId);

    void createApi(Long projectId, CreateApiReq req);

    void updateApi(Api oldApi, CreateApiReq req);

    void deleteApi(Api oldApi);

    void batchImportApi(BatchImportApiReq req);

    Long countApi(Long projectId);
}
