package com.bestpractice.api.infrastrucuture.persistent.rdbms;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

@Override
  public void testRemoveById() {
    // GIVEN an existing Info in the database
    Info existingInfo = new Info();
    existingInfo.setId(newId());
    existingInfo.setTitle("Existing Title");
    existingInfo.setDescription("Existing Description");

    // WHEN the Info is removed by ID
    // THEN the Info should no longer exist in the database
    boolean removed = removeById("testId");

    assertTrue(removed, "Removal should be successful");
    assertFalse(findById("testId").isEmpty(), "Info should not exist after removal");
  }
}
