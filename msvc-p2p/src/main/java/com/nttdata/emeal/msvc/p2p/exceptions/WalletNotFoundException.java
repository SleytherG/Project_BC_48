package com.nttdata.emeal.msvc.p2p.exceptions;

public class WalletNotFoundException extends RuntimeException {

  public WalletNotFoundException(String message) {
    super(message);
  }
}
