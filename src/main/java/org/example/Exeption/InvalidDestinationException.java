package org.example.Exeption;

public class InvalidDestinationException extends RuntimeException {
  public InvalidDestinationException(String message) {
    super(message);
  }
}
