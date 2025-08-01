package com.bestpractice.api.infrastrucuture.persistent.rdbms;

@BeforeEach

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
void setUp() {
    repository = new RdbmsInfoPersistentRepository(new JdbcTemplate());
    info = new Info();
    info.setId(repository.newId());
    info.setTitle("Test Title");
    info.setDescription("Test Description");
}
