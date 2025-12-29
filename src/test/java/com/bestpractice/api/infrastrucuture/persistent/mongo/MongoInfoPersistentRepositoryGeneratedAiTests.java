package com.bestpractice.api.infrastrucuture.persistent.mongo;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;

@Test
    public void testSave_ReturnsSavedInfo_WhenValidDataProvided() {
        // Arrange
        Info info = new Info("id1", "title1", "content1");
        when(mongoClient.getDatabase("test")).thenReturn(mongoDatabase);
        when(mongoDatabase.getCollection("info", MongoInfoEntity.class)).thenReturn(mongoCollection);

        // Act
        Info savedInfo = infoPersistentRepository.save(info);

        // Assert
        assertNotNull(savedInfo);
        assertEquals("title1", savedInfo.getTitle());
        assertEquals("content1", savedInfo.getContent());
    }
}
