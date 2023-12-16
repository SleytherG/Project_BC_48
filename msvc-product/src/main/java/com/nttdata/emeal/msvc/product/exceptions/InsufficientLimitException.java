package com.nttdata.emeal.msvc.product.exceptions;

public class InsufficientLimitException extends RuntimeException {

  public InsufficientLimitException(String message) {
    super(message);
  }
}
