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
public class CheckingAccount extends BankAccount {

  private String maintenanceFee;
  private List<String> holders;
  private List<String> signatories;
}
