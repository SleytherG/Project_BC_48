package com.nttdata.emeal.msvc.yanki.model;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BankAccount {

  private String id;
  private String idClient;
  private String productType;
  private String accountBankType;
  private BigDecimal balance;
  private Boolean isMainAccount;

}
