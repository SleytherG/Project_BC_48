package com.nttdata.emeal.msvc.transaction.exceptions;

public class TransactionNotFoundException extends RuntimeException {
  public TransactionNotFoundException(String message) {
    super(message);
  }
}
