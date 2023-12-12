package com.nttdata.emeal.msvc.transaction.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Document(value = "transactions")
public class Transaction {

  @Id
  private String id;

  private String dateTransaction;

  private String transactionType;
}
