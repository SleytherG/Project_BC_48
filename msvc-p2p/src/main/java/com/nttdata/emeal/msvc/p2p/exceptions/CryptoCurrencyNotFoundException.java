package com.nttdata.emeal.msvc.p2p.exceptions;

public class CryptoCurrencyNotFoundException extends RuntimeException {

  public CryptoCurrencyNotFoundException(String message) {
    super(message);
  }
}
