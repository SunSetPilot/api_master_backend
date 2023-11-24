package com.coderalliance.apimaster.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.coderalliance.apimaster.dao.ApiMapper;
import com.coderalliance.apimaster.exception.BusinessException;
import com.coderalliance.apimaster.model.entity.Api;
import com.coderalliance.apimaster.model.vo.req.BatchImportApiReq;
import com.coderalliance.apimaster.model.vo.req.CreateApiReq;
import com.coderalliance.apimaster.model.vo.resq.ApiListResp;
import com.coderalliance.apimaster.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApiServiceImpl implements ApiService {

    @Autowired
    ApiMapper apiMapper;

    @Override
    public List<ApiListResp> getApiList(Long projectId) {
        QueryWrapper<Api> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Api::getProjectId, projectId);
        List<Api> apis = apiMapper.selectList(queryWrapper);
        return apis.stream().map(api -> ApiListResp.builder()
                .id(api.getId())
                .description(api.getDescription())
                .protocol(api.getProtocol())
                .path(api.getPath())
                .method(api.getMethod())
                .headerParams(api.getHeaderParams())
                .queryParams(api.getQueryParams())
                .bodyParams(api.getBodyParams())
                .response(api.getResponse())
                .build()).collect(Collectors.toList());
    }

    @Override
    public void createApi(Long project_id, CreateApiReq req) {
        // todo update project api count
        Api newApi = Api.builder()
                .projectId(project_id)
                .description(req.getDescription())
                .protocol(req.getProtocol())
                .path(req.getPath())
                .method(req.getMethod())
                .headerParams(req.getHeaderParams())
                .queryParams(req.getQueryParams())
                .bodyParams(req.getBodyParams())
                .response(req.getResponse())
                .build();
        apiMapper.insert(newApi);
    }

    @Override
    public void updateApi(Long apiId, CreateApiReq req) {
        Api oldApi = apiMapper.selectById(apiId);
        if (oldApi == null) {
            throw new BusinessException("api not exist!");
        }
        Api newApi = Api.builder()
                .id(apiId)
                .projectId(oldApi.getProjectId())
                .description(req.getDescription())
                .protocol(req.getProtocol())
                .path(req.getPath())
                .method(req.getMethod())
                .headerParams(req.getHeaderParams())
                .queryParams(req.getQueryParams())
                .bodyParams(req.getBodyParams())
                .response(req.getResponse())
                .build();
        apiMapper.updateById(newApi);
    }

    @Override
    public void deleteApi(Long apiId) {
        // todo update project api count
        apiMapper.deleteById(apiId);
    }

    @Override
    public void batchImportApi(BatchImportApiReq req) {
        // todo update project api count
    }
}
