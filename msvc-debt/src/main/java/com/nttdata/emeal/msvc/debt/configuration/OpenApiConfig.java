package com.nttdata.emeal.msvc.debt.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
  info = @Info(
    title = "API Debts V1",
    description = "API que realiza las operaciones CRUD para las deudas de clientes del banco.",
    version = "1.0.1"
  )
)
public class OpenApiConfig {
}
