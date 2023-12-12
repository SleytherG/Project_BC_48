package com.nttdata.emeal.msvc.transaction.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransactionNotFoundExceptionTest {

  @Test
  void testConstructor() {
    // Arrange
    String message = "Transaction not found";

    // Act
    TransactionNotFoundException exception = new TransactionNotFoundException(message);

    // Assert
    assertEquals(message, exception.getMessage());
  }

}