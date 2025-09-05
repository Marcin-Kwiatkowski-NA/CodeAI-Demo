package com.bestpractice.api.domain.component;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

// WHEN the setRequestId() method is called with a new requestId
  // THEN the requestId should be updated
    requestInfoComponent.setRequestId("newRequestId");
    assertEquals("newRequestId", requestInfoComponent.getRequestId());
  }
}
