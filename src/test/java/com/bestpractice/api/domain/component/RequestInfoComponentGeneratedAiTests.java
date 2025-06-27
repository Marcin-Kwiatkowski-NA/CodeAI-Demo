package com.bestpractice.api.domain.component;

  @Test

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
  void getRequestId() {
    // GIVEN: A new RequestInfoComponent instance is created.
    // WHEN: The setRequestId() method is called with a non-null value.
    // THEN: The requestId property is set to the provided value.
    requestInfoComponent.setRequestId("testRequestId");
    assert requestInfoComponent.getRequestId() != null && requestInfoComponent.getRequestId().equals("testRequestId");
  }
