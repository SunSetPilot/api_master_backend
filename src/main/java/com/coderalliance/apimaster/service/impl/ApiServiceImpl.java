package com.coderalliance.apimaster.service.impl;

import com.coderalliance.apimaster.dao.ApiMapper;
import com.coderalliance.apimaster.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApiServiceImpl implements ApiService {
    @Autowired
    ApiMapper apiMapper;
}
