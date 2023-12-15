package com.nttdata.emeal.msvc.product.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Document(value = "debts")
public class Debt {

  @Id
  private String id;
  private BigDecimal amount;
  private String idProduct;
  private String expirationDate;
}
