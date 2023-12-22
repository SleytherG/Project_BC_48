package com.nttdata.emeal.msvc.product.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Document(value = "products")
public class Product {

  private String id;
  private String idClient;
  private String productType;
}
