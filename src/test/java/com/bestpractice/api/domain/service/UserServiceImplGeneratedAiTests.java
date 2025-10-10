package com.bestpractice.api.domain.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import com.bestpractice.api.infrastrucuture.entity.User;
import org.springframework.context.annotation.Profile;

@Profile("!test")
public interface UserPersistentRepository {
    String newId();

    User findByEmail(String email);

    User findById(String id);

    User insert(User user);

    User replace(String id, User user);

    boolean removeById(String id);
}
