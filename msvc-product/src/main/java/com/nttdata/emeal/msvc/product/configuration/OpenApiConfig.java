package com.nttdata.emeal.msvc.product.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
  info = @Info(
    title = "API Products V1",
    description = "API que realiza las operaciones CRUD para productos que ofrece el banco.",
    version = "1.0.1"
  )
)
public class OpenApiConfig {
}
