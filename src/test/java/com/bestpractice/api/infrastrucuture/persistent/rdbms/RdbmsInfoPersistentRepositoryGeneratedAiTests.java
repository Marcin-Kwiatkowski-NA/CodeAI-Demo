package com.bestpractice.api.infrastrucuture.persistent.rdbms;

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
void shouldReplaceInfo() {
    // GIVEN
    Info result = repository.replace("id1", new Info().setId("id1").setTitle("new-title").setDescription("new-desc"));

    // THEN
    assertThat(result).isNotNull();
    assertThat(result.getTitle()).isEqualTo("new-title");
    assertThat(result.getDescription()).isEqualTo("new-desc");
}
