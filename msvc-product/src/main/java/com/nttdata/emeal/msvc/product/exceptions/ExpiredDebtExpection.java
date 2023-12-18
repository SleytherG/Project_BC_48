package com.nttdata.emeal.msvc.product.exceptions;

public class ExpiredDebtExpection extends RuntimeException {

  public ExpiredDebtExpection(String message) {
    super(message);
  }
}
