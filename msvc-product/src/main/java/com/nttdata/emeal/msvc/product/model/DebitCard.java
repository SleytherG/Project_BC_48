package com.nttdata.emeal.msvc.product.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DebitCard extends BankAccount {

  private List<String> accountsAssociated;
  private String mainAccountProductId;

  public void addAccountProductIdToList(String productId) {
    accountsAssociated.add(productId);
  }
}
