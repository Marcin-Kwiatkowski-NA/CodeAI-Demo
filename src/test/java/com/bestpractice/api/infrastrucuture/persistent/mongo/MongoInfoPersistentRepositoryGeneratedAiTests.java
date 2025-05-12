package com.bestpractice.api.infrastrucuture.persistent.api.infrastrucuture.entity.MongoInfoEntity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import com.bestpractice.api.infrastrucuture.persistent.mongo.MongoInfo;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static com.bestpractice.api.infrastrucuture.persistent.mongo.MongoInfo.class;

import java.util.ArrayList;
import java.util.List;

class MongoInfoPersistentRepositoryTest {

    @Test
    void testNewId() {
        MongoInfoInfo entity = new MongoInfoInfo();
        entity.setId(ObjectId.generate());
        assertEquals("1234567890", entity.getId());
    }

    @Test
    void testFindById() {
        MongoInfoInfo entity = new MongoInfoInfo();
        entity.setId(ObjectId.generate());
        MongoInfo.class.find(entity).get(0)
        assertEquals("1234567890", entity.getId());
    }

    @Test
    void testFindAll() {
        MongoInfoInfo entity = new MongoInfoInfo();
        entity.setId(ObjectId.generate());
        MongoInfo.class.find(entity).get(0)
        assertEquals("1234567890", entity.getId());
    }

    @Test
    void testRemoveById() {
        MongoInfoInfo entity = new MongoInfoInfo();
        entity.setId(ObjectId.generate());
        MongoInfo.class.removeById(entity.getId())
        assertEquals("1234567890", entity.getId());
    }

    @Test
    void testInsert() {
        MongoInfoInfo entity = new MongoInfoInfo();
        entity.setId(ObjectId.generate());
        MongoInfo.class.insert(entity);
        assertEquals("1234567890", entity.getId());
    }

    @Test
    void testReplace() {
        MongoInfoInfo entity = new MongoInfoInfo();
        entity.setId(ObjectId.generate());
        MongoInfo.class.replace(entity.getId(), entity);
        assertEquals("1234567890", entity.getId());
    }

    @Test
    void testgetById() {
        MongoInfoInfo entity = new MongoInfoInfo();
        entity.setId(ObjectId.generate());
        MongoInfo.class.getById(entity.getId())
        assertEquals("1234567890", entity.getId());
    }
}
