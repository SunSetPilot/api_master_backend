package com.coderalliance.apimaster.service.impl;

import com.coderalliance.apimaster.dao.ApiMapper;
import com.coderalliance.apimaster.model.vo.req.BatchImportApiReq;
import com.coderalliance.apimaster.model.vo.req.CreateApiReq;
import com.coderalliance.apimaster.model.vo.resq.ApiListResp;
import com.coderalliance.apimaster.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiServiceImpl implements ApiService {
    @Autowired
    ApiMapper apiMapper;

    @Override
    public List<ApiListResp> getApiList(Long projectId) {
        return null;
    }

    @Override
    public Boolean createApi(CreateApiReq req) {
        return null;
    }

    @Override
    public Boolean updateApi(Long apiId, CreateApiReq req) {
        return null;
    }

    @Override
    public Boolean deleteApi(Long apiId) {
        return null;
    }

    @Override
    public Boolean batchImportApi(BatchImportApiReq req) {
        return null;
    }
}
