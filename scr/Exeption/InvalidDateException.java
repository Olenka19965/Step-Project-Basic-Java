package scr.Exeption;

public class InvalidDateException extends RuntimeException {
    public InvalidDateException(String message) {
      super(message);
    }
}
