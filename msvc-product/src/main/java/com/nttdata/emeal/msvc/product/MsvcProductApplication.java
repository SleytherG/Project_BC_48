package com.nttdata.emeal.msvc.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MsvcProductApplication {

  public static void main(String[] args) {
    SpringApplication.run(MsvcProductApplication.class, args);
  }

}
