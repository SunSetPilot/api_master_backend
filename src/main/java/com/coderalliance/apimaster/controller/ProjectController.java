package com.coderalliance.apimaster.controller;

import com.coderalliance.apimaster.service.PermissionService;
import com.coderalliance.apimaster.service.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/projects")
@Slf4j
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private PermissionService permissionService;
}
