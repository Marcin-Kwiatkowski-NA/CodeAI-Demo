package com.bestpractice.api.infrastrucuture.entity;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.mockito.Mockito;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class SharedDataGeneratedAiTests {

    @Test
    public void givenNullCreatedAt_whenOnPrePersistCalledThenCreatedAtIsInitializedWithCurrentTime() {
        // GIVEN
        SharedData sharedData = new SharedData();
        sharedData.setCreatedAt(null);

        // WHEN
        sharedData.onPrePersist();

        // THEN
        assertThat(sharedData.getCreatedAt()).isNotNull();
        assertThat(sharedData.getCreatedAt()).isInstanceOf(Date.class);
    }
}
