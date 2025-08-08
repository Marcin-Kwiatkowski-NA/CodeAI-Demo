package com.bestpractice.api.infrastrucuture.persistent.cassandra;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;

@Test
  @DisplayName("Insert")
  void insert() {
    Info info1 = new Info();
    info1.setId("id1");
    info1.setTitle("title1");
    info1.setDescription("description1");

    Mockito.when(mockCqlSession.execute(Mockito.any())).thenReturn(
        Mockito.any());

    Info insertedInfo = repository.insert(info1);
    assertNotNull(insertedInfo);
    assertEquals("id1", insertedInfo.getId());
    assertEquals("title1", insertedInfo.getTitle());
    assertEquals("description1", insertedInfo.getDescription());
  }
