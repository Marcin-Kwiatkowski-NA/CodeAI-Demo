package com.bestpractice.api.infrastrucuture;

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
void shouldReturnEmptyListWhenNoInfosExist() {
    // GIVEN
    // Mock the session behavior to return empty result set
    PreparedStatement selectAllStatement = Mockito.mock(PreparedStatement.class);
    ResultSet resultSet = Mockito.mock(ResultSet.class);
    when(resultSet.all()).thenReturn(List.of());

    when(session.prepare(any())).thenReturn(selectAllStatement);
    when(selectAllStatement.bind()).thenReturn(resultSet);

    // WHEN
    List<Info> result = repository.findAll();

    // THEN
    assertThat(result).isNotNull();
    assertThat(result).isEmpty();
}
