package com.nttdata.emeal.msvc.commission.exceptions;

public class CommissionNotFoundException extends RuntimeException {
  public CommissionNotFoundException(String message) {
    super(message);
  }
}
