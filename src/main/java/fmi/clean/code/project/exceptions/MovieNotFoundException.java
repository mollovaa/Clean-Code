package fmi.clean.code.project.exceptions;

public class MovieNotFoundException extends RuntimeException {

  public MovieNotFoundException(String message) {
    super(message);
  }
}
