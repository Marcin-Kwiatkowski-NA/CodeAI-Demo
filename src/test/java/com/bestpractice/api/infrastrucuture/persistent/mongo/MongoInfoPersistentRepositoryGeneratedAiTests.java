package com.bestpractice.api.infrastrucuture.persistent.mongo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.bson.ObjectId;

class MongoRepositoryTest {

    @Test
    void testCreateObjectId() {
        ObjectId id = new ObjectId();
        String idString = id.toHexString();
        Assertions.assertEquals("\\d+", idString);
        Assertions.assertNotNull(idString);
    }
}
