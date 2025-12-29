package com. bestpractice. api. infrastructure. persistent. cassandra;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org. assertj. core. api. Assertions. assertThat;

import static org. assertj. core. api. Assertions. assertThatThrownBy;

import org. junit. jupiter. api. Test;

import org. junit. jupiter. api. AfterEach;

import org. junit. jupiter. api. AfterAll;

import org. junit. jupiter. api. extension. ExtendWith;

import static org. junit. jupiter. api. Assertions. assertThrows;

import org. junit. jupiter. api. BeforeAll;

import org. junit. jupiter. api. BeforeEach;

import org. junit. jupiter. api. extension. ExtendWith;

import org. mockito. InjectMocks;

import org. mockito. Mock;

import org. mockito. junit. jupiter. MockitoExtension;

import java. util. UUID;

import static org. mockito. Mockito. when;

@ExtendWith( MockitoExtension. class)

public class CassandraUserPersistentRepositoryGeneratedAiTests {

    @InjectMocks
    private CassandraUserPersistentRepository repository;

    @Mock
    private UserPersistentRepository userPersistentRepository;

    private User user;

    private String userId;

    @BeforeEach
    void setUp() {

        userId = UUID. randomUUID(). toString();

        user = new User( userId, " testUser", " test@ example. com", " password123");

    }

    @Test
    void newId_ ShouldGenerateNewId() {

        // GIVEN: No preconditions needed
        // WHEN: Calling newId method
        String newId = repository. newId();

        // THEN: The generated ID should not be null
        assertThat( newId). isNotNull();

    }

    @Test
    void findByEmail_ ShouldReturnUser_ WhenUserExists() {

        // GIVEN: A user exists in the repository
        when( userPersistentRepository. findByEmail( user. getEmail())). thenReturn( user);

        // WHEN: Searching by email
        User foundUser = repository. findByEmail( user. getEmail());

        // THEN: The found user should match the expected user
        assertThat( foundUser). isEqualTo( user);

    }

    @Test
    void findById_ ShouldReturnUser_ WhenUserExists() {

        // GIVEN: A user exists in the repository
        when( userPersistentRepository. findById( userId)). thenReturn( user);

        // WHEN: Searching by ID
        User foundUser = repository. findById( userId);

        // THEN: The found user should match the expected user
        assertThat( foundUser). isEqualTo( user);

    }

    @Test
    void insert_ ShouldInsertUser() {

        // GIVEN: A new user to be inserted
        // WHEN: Inserting the user
        User insertedUser = repository. insert( user);

        // THEN: The inserted user should match the expected user
        assertThat( insertedUser). isEqualTo( user);

    }

    @Test
    void replace_ ShouldReplaceUser() {

        // GIVEN: A user to be replaced
        // WHEN: Replacing the user
        User replacedUser = repository. replace( userId, user);

        // THEN: The replaced user should match the expected user
        assertThat( replacedUser). isEqualTo( user);

    }

    @Test
    void removeById_ ShouldRemoveUser() {

        // GIVEN: A user to be removed
        // WHEN: Removing the user
        boolean isRemoved = repository. removeById( userId);

        // THEN: The user should be removed
        assertThat( isRemoved). isTrue();

    }

}
