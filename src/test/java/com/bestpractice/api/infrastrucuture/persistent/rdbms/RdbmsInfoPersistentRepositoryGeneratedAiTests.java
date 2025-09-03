package com.bestpractice.api.infrastrucuture.persistent.rdbms;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import com.bestpractice.api.infrastrucuture.entity.Info;
import com.bestpractice.api.infrastrucuture.persistent.InfoPersistentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.jdbc.core.JdbcTemplate;

@ExtendWith(RdbmsInfoPersistentRepositoryGeneratedAiTests.class)
class RdbmsInfoPersistentRepositoryGeneratedAiTests {

    private RdbmsInfoPersistentRepository repository;
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        jdbcTemplate = new JdbcTemplate();
        repository = new RdbmsInfoPersistentRepository(jdbcTemplate);
    }
}

class RdbmsInfoPersistentRepositoryGeneratedAiTests {

    @ExtendWith(RdbmsInfoPersistentRepositoryGeneratedAiTests.class)
    class RdbmsInfoPersistentRepositoryGeneratedAiTests {

        private RdbmsInfoPersistentRepository repository;
        private JdbcTemplate jdbcTemplate;

        @BeforeEach
        void setUp() {
            jdbcTemplate = new JdbcTemplate();
            repository = new RdbmsInfoPersistentRepository(jdbcTemplate);
        }

        @Test
        void newId() {
            String id = repository.newId();
            assertNotNull(id);
            assertTrue(id.length() > 0);
        }

        @Test
        void findAll() {
            // Arrange
            var info = new Info();
            info.setId("testId");
            info.setTitle("Test Title");
            info.setDescription("Test Description");

            // Act
            var allInfo = repository.findAll();

            // Assert
            assertEquals(1, allInfo.size());
            assertEquals("testId", allInfo.get(0).getId());
            assertEquals("Test Title", allInfo.get(0).getTitle());
            assertEquals("Test Description", allInfo.get(0).getDescription());
        }

        @Test
        void findById() {
            // Arrange
            var info = new Info();
            info.setId("testId");
            info.setTitle("Test Title");
            info.setDescription("Test Description");

            // Act
            var foundInfo = repository.findById("testId");

            // Assert
            assertEquals("testId", foundInfo.getId());
            assertEquals("Test Title", foundInfo.getTitle());
            assertEquals("Test Description", foundInfo.getDescription());
        }

        @Test
        void insert() {
            // Arrange
            var info = new Info();
            info.setId("testId");
            info.setTitle("Test Title");
            info.setDescription("Test Description");

            // Act
            var insertedInfo = repository.insert(info);

            // Assert
            assertEquals("testId", insertedInfo.getId());
            assertEquals("Test Title", insertedInfo.getTitle());
            assertEquals("Test Description", insertedInfo.getDescription());
        }

        @Test
        void replace() {
            // Arrange
            var info = new Info();
            info.setId("testId");
            info.setTitle("Test Title");
            info.setDescription("Test Description");

            // Act
            var replacedInfo = repository.replace("testId", info);

            // Assert
            assertEquals("testId", replacedInfo.getId());
            assertEquals("Test Title", replacedInfo.getTitle());
            assertEquals("Test Description", replacedInfo.getDescription());
        }

        @Test
        void removeById() {
            // Arrange
            var info = new Info();
            info.setId("testId");
            info.setTitle("Test Title");
            info.setDescription("Test Description");

            // Act
            boolean removed = repository.removeById("testId");

            // Assert
            assertTrue(removed);
        }
    }
}