package com.bestpractice.api.domain.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.UnAuthorized;
import com.bestpractice.api.domain.component.BCryptPasswordEncryptionComponent;
import com.bestpractice.api.domain.model.UserRequest;
import com.bestpractice.api.domain.model.User;
import com.bestpractice.api.domain.model.UserResponse;
import com.bestpractice.api.infrastrucuture.entity.User as InfrastructureUser;
import com.bestpractice.api.infrastrucuture.persistent.UserPersistentRepository;

@ExtendWith(MyExtension.class)
class UserServiceImplGeneratedAiTests {

  private UserServiceImpl userService;
  private UserPersistentRepository userRepository;
  private BCryptPasswordEncryptionComponent encryptionComponent;
  private InfrastructureUser user;

  @BeforeEach
  void setUp() {
    userRepository = new UserPersistentRepository() {
      @Override
      public InfrastructureUser findByEmail(String email) {
        return user;
      }

      @Override
      public InfrastructureUser newId() {
        return user;
      }

      @Override
      public InfrastructureUser insert(InfrastructureUser user) {
        return user;
      }
    };
    encryptionComponent = new BCryptPasswordEncryptionComponent() {
      @Override
      public String encodePassword(String password) {
        return password;
      }

      @Override
      public boolean matchedPassword(String password, String rawPw) {
        return rawPw.equals(password);
      }
    };
    user = new InfrastructureUser();
    user.setId("testId");
    user.setUsername("testUser");
    user.setEmail("test@example.com");
    user.setPassword("testPassword");
    userService = new UserServiceImpl(userRepository, encryptionComponent);
  }

  @Test
  void getUserById() {
    InfrastructureUser retrievedUser = userService.getUserById("testId");
    assert retrievedUser != null;
    assert retrievedUser.getId().equals("testId");
    assert retrievedUser.getUsername().equals("testUser");
    assert retrievedUser.getEmail().equals("test@example.com");
  }

  @Test
  void getAuthenticatedUser() {
    String email = "test@example.com";
    String rawPw = "testPassword";
    String authenticatedUser = userService.getAuthenticatedUser(email, rawPw);
    assert authenticatedUser != null;
    assert authenticatedUser.getId().equals("testId");
    assert authenticatedUser.getUsername().equals("testUser");
    assert authenticatedUser.getEmail().equals("test@example.com");
  }

  @Test
  void generateUser() {
    UserRequest request = new UserRequest();
    request.setUsername("newTestUser");
    request.setEmail("newTest@example.com");
    request.setPassword("newPassword");
    UserResponse response = userService.generateUser(request);
    assert response != null;
    assert response.getId().equals("testId");
    assert response.getUsername().equals("newTestUser");
    assert response.getEmail().equals("newTest@example.com");
  }
}

class MyExtension {}
