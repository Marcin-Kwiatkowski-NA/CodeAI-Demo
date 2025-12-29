package com.bestpractice.api.domain.service;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import static org.mockito.Mockito.mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponent;
import com.bestpractice.api.domain.model.UserRequest;
import com.bestpractice.api.infrastrucuture.entity.User;
import com.bestpractice.api.infrastrucuture.persistent.UserPersistentRepository;

class UserServiceImplGeneratedAiTests {

    @Mock
    private UserPersistentRepository userRepository;

    @Mock
    private BCryptPasswordEncryptionComponent encryptionComponent;

    private UserServiceImpl userServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userServiceImpl = new UserServiceImpl(userRepository, encryptionComponent);
    }

    @Test
    void shouldThrowInternalServerErrorWhenRepositoryThrowsUnexpectedException() {
        // GIVEN
        UserRequest request = new UserRequest();
        request.setUsername("user");
        request.setEmail("user@example.com");
        request.setPassword("pass");

        Mockito.when(userRepository.newId()).thenReturn("user-1");
        Mockito.when(encryptionComponent.encodePassword(any(String.class))).thenReturn("hashed-password");
        Mockito.when(userRepository.findByEmail(any(String.class))).thenReturn(null);
        Mockito.when(userRepository.insert(any(User.class))).thenThrow(new RuntimeException("Unexpected DB error"));

        // WHEN & THEN
        assertThatThrownBy(() -> userServiceImpl.generateUser(request))
                .isInstanceOf(InternalServerError.class);
    }
}
