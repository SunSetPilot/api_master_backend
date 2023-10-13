package com.coderalliance.apimaster;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.coderalliance.apimaster.dao")
public class ApiMasterBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiMasterBackendApplication.class, args);
	}
}
