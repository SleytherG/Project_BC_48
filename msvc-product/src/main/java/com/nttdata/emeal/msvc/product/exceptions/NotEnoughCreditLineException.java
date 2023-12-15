package com.nttdata.emeal.msvc.product.exceptions;

public class NotEnoughCreditLineException extends RuntimeException {

  public NotEnoughCreditLineException(String message) {
    super(message);
  }
}
