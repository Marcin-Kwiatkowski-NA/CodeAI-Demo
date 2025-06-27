package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

class MongoUserEntity {

    private String id;
    private String username;
    private String email;
    private String password;

    @TestInitialize
    void setUp(TestInfo testInfo) {
        id = null;
        username = null;
        email = null;
        password = null;
    }

    public String convertFrom(User user) {
        if (user == null) {
            return null;
        }
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.password = user.getPassword();
        return this;
    }
}
