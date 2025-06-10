package com.bestpractice.api.domain.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class UserServiceImplGeneratedAiTests {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindUserById() {
        Long id = 1L;
        User expectedUser = new User("John", "Doe");

        when(userRepository.findById(id)).thenReturn(expectedUser);

        User actualUser = userService.findUserById(id);

        assertEquals(expectedUser, actualUser);
    }

    @Test
    public void testSaveUser() {
        User newUser = new User("Jane", "Doe");

        when(userRepository.save(newUser)).thenReturn(newUser);

        User savedUser = userService.saveUser(newUser);

        assertEquals(newUser, savedUser);
    }
}
