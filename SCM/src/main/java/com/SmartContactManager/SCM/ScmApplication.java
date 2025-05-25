package com.SmartContactManager.SCM;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.SmartContactManager.SCM.Entities")
public class ScmApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScmApplication.class, args);
	}

}
