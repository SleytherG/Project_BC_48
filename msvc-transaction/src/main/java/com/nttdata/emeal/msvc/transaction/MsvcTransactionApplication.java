package com.nttdata.emeal.msvc.transaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MsvcTransactionApplication {

  public static void main(String[] args) {
    SpringApplication.run(MsvcTransactionApplication.class, args);
  }

}
