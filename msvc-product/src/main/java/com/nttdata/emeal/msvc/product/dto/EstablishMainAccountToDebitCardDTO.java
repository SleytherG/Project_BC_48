package com.nttdata.emeal.msvc.product.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EstablishMainAccountToDebitCardDTO {

  private String debitCardId;
  private String mainAccountId;

}
