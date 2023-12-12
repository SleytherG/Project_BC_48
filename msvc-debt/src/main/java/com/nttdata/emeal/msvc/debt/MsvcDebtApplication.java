package com.nttdata.emeal.msvc.debt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MsvcDebtApplication {

  public static void main(String[] args) {
    SpringApplication.run(MsvcDebtApplication.class, args);
  }

}
