package com.bestpractice.api.common.exception;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

public class Forbidden extends RuntimeException {
  public Forbidden() {
    throw new RuntimeException("Forbidden exception");
  }

  public Forbidden(String msg) {
    super(msg);
  }

  public Forbidden(Throwable cause) {
    super(cause);
  }

  public Forbidden(String msg, Throwable cause) {
    super(msg, cause);
  }
}
