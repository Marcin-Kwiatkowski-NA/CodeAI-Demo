package com.bestpractice.api.infrastrucuture.persistent.mongo;

import static org.mockito.Mockito.mock;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.Mockito.when;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class MongoUserPersistentRepositoryGeneratedAiTests {

    @InjectMocks
    private MongoUserPersistentRepository mongoUserPersistentRepository;

    @Mock
    private MongoTemplate mongoTemplate;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId("1");
        user.setUsername("testUser");
        user.setEmail("test@domain.com");
        user.setPassword("password");
    }

    @Test
    void findByEmail_shouldReturnUser_WhenUserExists() {
        // GIVEN
        when(mongoTemplate.findOne(any(Query.class), eq(User.class))).thenReturn(user);

        // WHEN
        User foundUser = mongoUserPersistentRepository.findByEmail("test@domain.com");

        // THEN
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getEmail()).isEqualTo("test@domain.com");
        verify(mongoTemplate, times(1)).findOne(any(Query.class), eq(User.class));
    }

    @Test
    void findById_shouldReturnUser_WhenUserExists() {
        // GIVEN
        when(mongoTemplate.findById("1", User.class)).thenReturn(user);

        // WHEN
        User foundUser = mongoUserPersistentRepository.findById("1");

        // THEN
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getId()).isEqualTo("1");
        verify(mongoTemplate, times(1)).findById("1", User.class);
    }

    @Test
    void insert_shouldSaveUser() {
        // GIVEN
        when(mongoTemplate.save(user)).thenReturn(user);

        // WHEN
        User savedUser = mongoUserPersistentRepository.insert(user);

        // THEN
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getId()).isEqualTo("1");
        verify(mongoTemplate, times(1)).save(user);
    }

    @Test
    void replace_shouldUpdateUser() {
        // GIVEN
        when(mongoTemplate.save(user)).thenReturn(user);

        // WHEN
        User updatedUser = mongoUserPersistentRepository.replace(user);

        // THEN
        assertThat(updatedUser).isNotNull();
        assertThat(updatedUser.getId()).isEqualTo("1");
        verify(mongoTemplate, times(1)).save(user);
    }

    @Test
    void removeById_shouldDeleteUser() {
        // GIVEN
        when(mongoTemplate.remove(any(Query.class))).thenReturn(Optional.of(user));

        // WHEN
        boolean isDeleted = mongoUserPersistentRepository.removeById("1");

        // THEN
        assertThat(isDeleted).isTrue();
        verify(mongoTemplate, times(1)).remove(any(Query.class));
    }
}
