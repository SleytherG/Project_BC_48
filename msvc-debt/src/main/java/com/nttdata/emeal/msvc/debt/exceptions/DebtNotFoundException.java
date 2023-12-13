package com.nttdata.emeal.msvc.debt.exceptions;

public class DebtNotFoundException extends RuntimeException {

  public DebtNotFoundException(String message) {
    super(message);
  }

  public DebtNotFoundException() {
    super("Default message for DebtNotFoundException");
  }
}
