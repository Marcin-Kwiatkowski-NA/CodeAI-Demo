package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import com.bestpractice.api.infrastrucuture.entity.SharedData;

package com.bestpractice.api.infrastrucuture.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;

public class User extends SharedData implements Serializable {

    @Column(name = "id")
    private String  id;

    @Column(nullable = false, name = "username")
    private String username;

    @Column(nullable = false, name = "email")
    private String email;

    @NotNull
    @Column(nullable = false, name = "password")
    private String password;

    public User() {
    }

    public User(String id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

package com.bestpractice.api.domain.model;

import org.junit.jupiter.api.BeforeEach;
import com.bestpractice.api.infrastrucuture.entity.SharedData;

public class UserRequestGeneratedAiTests {

    private UserRequest userRequest;

    @BeforeEach
    void setUp() {
        userRequest = new UserRequest();
    }

    @org.junit.jupiter.api.Test
    void testConvert_validInput() {
        // GIVEN
        String id = "123";
        String encodePw = "password123";

        // WHEN
        User user = userRequest.convert(id, encodePw);

        // THEN
        assert user.getId().equals(id);
        assert user.getPassword().equals(encodePw);
        assert user.getEmail().equals(userRequest.getEmail());
        assert user.getUsername().equals(userRequest.getUsername());
    }
}
