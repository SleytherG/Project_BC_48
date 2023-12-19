package com.nttdata.emeal.msvc.yanki.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
  info = @Info(
    title = "API Yanki V1",
    description = "API que realiza las operaciones CRUD del monedero movil Yanki del banco.",
    version = "1.0.1"
  )
)
public class OpenApiConfig {
}
