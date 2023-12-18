package com.nttdata.emeal.msvc.product.dto;

import com.nttdata.emeal.msvc.product.model.BankAccount;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AssociateAccountToDebitCardDTO {

  private String productId;
}
