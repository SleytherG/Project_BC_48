package com.nttdata.emeal.msvc.p2p.exceptions;

public class CryptoCurrencyTransactionNotFoundException extends RuntimeException {

  public CryptoCurrencyTransactionNotFoundException(String message) {
    super(message);
  }
}
