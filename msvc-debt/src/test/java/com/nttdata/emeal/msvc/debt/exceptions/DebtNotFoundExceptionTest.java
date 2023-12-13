package com.nttdata.emeal.msvc.debt.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DebtNotFoundExceptionTest {


  @Test
  void testConstructorShouldSetMessage() {
    // Arrange
    String errorMessage = "Debt not found.";

    // Act
    DebtNotFoundException exception = new DebtNotFoundException(errorMessage);

    // Assert
    assertEquals(errorMessage, exception.getMessage());
  }

  @Test
  void constructorShouldSetDefaultMessage() {
    // Arrange
    String defaultErrorMessage = "Default message for DebtNotFoundException";

    // Act
    DebtNotFoundException exception = new DebtNotFoundException();

    // Assert
    assertEquals(defaultErrorMessage, exception.getMessage());
  }
}