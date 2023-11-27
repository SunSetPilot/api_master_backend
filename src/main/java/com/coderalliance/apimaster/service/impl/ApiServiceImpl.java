package com.coderalliance.apimaster.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.coderalliance.apimaster.dao.ApiMapper;
import com.coderalliance.apimaster.exception.BusinessException;
import com.coderalliance.apimaster.model.entity.Api;
import com.coderalliance.apimaster.model.vo.req.BatchImportApiReq;
import com.coderalliance.apimaster.model.vo.req.CreateApiReq;
import com.coderalliance.apimaster.service.ApiService;
import com.coderalliance.apimaster.service.rpc.ApiScanner;
import com.coderalliance.apimaster.service.rpc.ScanApiServiceGrpc.ScanApiServiceBlockingStub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.devh.boot.grpc.client.inject.GrpcClient;

import java.util.List;

@Service
public class ApiServiceImpl implements ApiService {

    @Autowired
    private ApiMapper apiMapper;

    @GrpcClient("scan-api-service")
    private ScanApiServiceBlockingStub scanApiServiceBlockingStub;

    @Override
    public List<Api> getApiList(Long projectId) {
        QueryWrapper<Api> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Api::getProjectId, projectId);
        return apiMapper.selectList(queryWrapper);
    }

    @Override
    public Api getApi(Long apiId) {
        Api api = apiMapper.selectById(apiId);
        if (api == null) {
            throw new BusinessException("api not exist");
        }
        return api;
    }

    @Override
    public Long createApi(Long projectId, CreateApiReq req) {
        Api newApi = Api.builder()
                .projectId(projectId)
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
        return newApi.getId();
    }

    @Override
    public void updateApi(Api oldApi, CreateApiReq req) {
        Api newApi = Api.builder()
                .id(oldApi.getId())
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
    public void deleteApi(Api oldApi) {
        apiMapper.deleteById(oldApi.getId());
    }

    @Override
    public void batchImportApi(BatchImportApiReq req) {
        if (req.getAutoImport()) {
            ApiScanner.ScanApiRequest scanApiRequest = ApiScanner.ScanApiRequest.newBuilder()
                    .setProjectId(req.getProjectId())
                    .setGitAddress(req.getGitAddress())
                    .setGitBranch(req.getGitBranch())
                    .build();
            ApiScanner.ScanApiResponse scanApiResponse = scanApiServiceBlockingStub.scan(scanApiRequest);
            if (!scanApiResponse.getSuccess()) {
                throw new BusinessException(scanApiResponse.getMessage());
            }
        }
    }

    @Override
    public Long countApi(Long projectId) {
        QueryWrapper<Api> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Api::getProjectId, projectId);
        return apiMapper.selectCount(queryWrapper);
    }

    @Override
    public void deleteApiByProjectId(Long id) {
        QueryWrapper<Api> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Api::getProjectId, id);
        apiMapper.delete(queryWrapper);
    }
}
