package com.dvlima.archetype.infrastructure.abstraction.validation;

public class ValidationError {

  private String message;

  public ValidationError(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
}
