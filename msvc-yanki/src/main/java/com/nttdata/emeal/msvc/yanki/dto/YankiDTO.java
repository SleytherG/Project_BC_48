package com.nttdata.emeal.msvc.yanki.dto;

import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class YankiDTO {

  private String documentNumber;
  private String documentType;
  private String phoneNumber;
  private String phoneImei;
  private String email;
  private BigDecimal balance;
  private String associatedAccount;

}
