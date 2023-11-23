package com.coderalliance.apimaster.controller;

import com.coderalliance.apimaster.service.ApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Slf4j
public class ApiController {

    @Autowired
    private ApiService apiService;
}
