package com.nttdata.emeal.msvc.debt.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Document(value = "debts")
public class Debt {

  @Id
  private String id;
  private String amount;
  private String idProduct;
  private String expirationDate;
}
