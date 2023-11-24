package com.coderalliance.apimaster.service.impl;

import com.coderalliance.apimaster.dao.ProjectMapper;
import com.coderalliance.apimaster.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectMapper projectMapper;
}
