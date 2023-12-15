package com.nttdata.emeal.msvc.product.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Transaction {

  private String id;

  private String dateTransaction;

  private String transactionType;

  private String productId;
}
