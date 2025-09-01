package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import com.bestpractice.api.infrastrucuture.entity.Info;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

@Test
public class MongoInfoEntityGeneratedAiTests {

    @Test
    public void testMongoInfoEntity() {
        // Create a dummy Info object
        Info info = new Info();
        info.setId(ObjectId.get());
        info.setName("Test Info");
        info.setValue(123);

        // Assert that the object is created correctly
        Assertions.assertEquals("Test Info", info.getName());
        Assertions.assertEquals(123, info.getValue());
        Assertions.assertNotNull(info.getId());
    }
}