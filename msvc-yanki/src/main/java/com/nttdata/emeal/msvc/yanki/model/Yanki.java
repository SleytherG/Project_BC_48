package com.nttdata.emeal.msvc.yanki.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Document(value = "yankis")
public class Yanki {

  @Id
  private String id;
  private String documentNumber;
  private String phoneNumber;
  private String phoneImei;
  private String email;
  private BigDecimal balance;
  private String associatedAccount;

}
