package com.nttdata.emeal.msvc.commission.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
  info = @Info(
    title = "API Commission V1",
    description = "API que realiza las operaciones CRUD de las comisiones generadas por transacciones del banco.",
    version = "1.0.1"
  )
)
public class OpenApiConfig {
}
