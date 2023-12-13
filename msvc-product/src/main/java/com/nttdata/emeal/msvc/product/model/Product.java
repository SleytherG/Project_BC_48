package com.nttdata.emeal.msvc.product.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Product {

  private String id;
  private String idClient;
  private String productType;
}
