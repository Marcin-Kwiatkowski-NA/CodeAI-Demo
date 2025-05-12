package com.bestpractice.api.app.v1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.ExtensionPurpose;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;

import java.util.ArrayList;
import java.util.List;

@ExtensionPurpose
@ExtensionPurpose(
    name = "AuthControllerTest",
    description = "Test class for AuthController"
)
public class AuthControllerTest {

    private final AuthService authService;

    @Test
    void testLoginWithValidData() {
        // Arrange
        ArrayList<AuthByEmailRequest> requests = new ArrayList<>();
        AuthByEmailRequest request = new AuthByEmailRequest("test@example.com", "password123");
        requests.add(request);

        // Act
        AuthResponse response = authService.login(request.getEmail(), request.getPassword());

        // Assert
        assertThrows(BadRequest.class, () -> {
            assertThrows(IllegalArgumentException.class, () -> {
                request.getEmail() != "test@example.com" || request.getPassword() != "password123";
            });
        });
    }

    @Test
    void testLoginWithInvalidData() {
        // Arrange
        ArrayList<AuthByEmailRequest> requests = new ArrayList<>();
        AuthByEmailRequest request = new AuthByEmailRequest("test@example.com", "password123");
        requests.add(request);

        // Act
        AuthResponse response = authService.login(request.getEmail(), request.getPassword());

        // Assert
        assertThrows(BadRequest.class, () -> {
            assertThrows(IllegalArgumentException.class, () -> {
                request.getEmail() != "test@example.com" || request.getPassword() != "password123";
            });
        });
    }

    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    @PostMapping(value = "/refreshtoken-login")
    public AuthResponse login(
            @RequestBody @Validated AuthByRefreshTokenRequest request,
            BindingResult bdResult
    ) {
        if (bdResult.hasErrors()) {
            throw new BadRequest(bdResult.getObjectName());
        }
        return this.authService.login(request.getRefreshToken());
    }
}