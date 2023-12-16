package com.nttdata.emeal.msvc.commission;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MsvcCommissionApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcCommissionApplication.class, args);
	}

}
