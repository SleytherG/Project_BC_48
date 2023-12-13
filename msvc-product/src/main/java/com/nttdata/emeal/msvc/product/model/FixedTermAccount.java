package com.nttdata.emeal.msvc.product.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FixedTermAccount extends BankAccount {

  private String specificDay;
}
