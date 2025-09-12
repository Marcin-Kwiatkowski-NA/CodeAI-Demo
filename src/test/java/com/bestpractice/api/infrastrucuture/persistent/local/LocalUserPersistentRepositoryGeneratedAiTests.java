package com.bestpractice.api.infrastrucuture.persistent.local;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;

public User replace(String id, User user) {
    Integer removeIndex = null;
    for (int i = 0; i < this.users.size(); i++) {
      if (!this.users.get(i).getId().equals(id)) {
        continue;
      }
      removeIndex = i;
      break;
    }
    if (removeIndex == null) {
      throw new RuntimeException("Data does not exist.");
    }

    this.users.set(removeIndex, user);
    return null;
}
