package com.nttdata.emeal.msvc.p2p.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
  info = @Info(
    title = "API P2P V1",
    description = "API que realiza las operaciones CRUD del servicio P2P del banco.",
    version = "1.0.1"
  )
)
public class OpenApiConfig {
}
