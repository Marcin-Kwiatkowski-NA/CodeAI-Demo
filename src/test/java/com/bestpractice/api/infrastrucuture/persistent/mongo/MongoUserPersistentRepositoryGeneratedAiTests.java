package com.bestpractice.api.infrastrucuture.persistent.mongo;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.infrastrucuture.entity.User;
import com.bestpractice.api.infrastrucuture.persistent.UserPersistentRepository;
import com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoUserEntity;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ReplaceOptions;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import java.util.Objects;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MongoUserPersistentRepositoryGeneratedAiTests.class)
class MongoUserPersistentRepositoryGeneratedAiTests {

    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;
    private MongoCollection<MongoUserEntity> collection;

    @BeforeEach
    void setUp() {
        // Mock MongoClient and MongoDatabase for testing purposes.
        // In a real application, these would be initialized with actual connections.
        mongoClient = new MongoClient();
        mongoDatabase = new MongoDatabase(mongoClient);
        collection = mongoDatabase.getCollection("users", MongoUserEntity.class);
    }

    @Test
    void newId_returnsValidObjectId() {
        // GIVEN: A new ObjectId is created.
        // WHEN: The newId() method is called.
        // THEN: The method returns a valid ObjectId string.
        String id = newId();
        assertNotNull(id);
        assertTrue(id.matches("\\d+"));
    }

    @Test
    void findByEmail_returnsUserWhenFound() {
        // GIVEN: A MongoUserEntity is created and stored in the collection.
        MongoUserEntity user = new MongoUserEntity("1", "testUser", "test@example.com", "password");
        collection.insertOne(user);

        // WHEN: The findByEmail() method is called with the email "test@example.com".
        // THEN: The method returns the MongoUserEntity with the matching email.
        MongoUserEntity foundUser = findByEmail("test@example.com");

        // Assert that the returned user has the correct email.
        assertEquals("test@example.com", foundUser.getEmail());
    }

    @Test
    void findById_returnsUserWhenFound() {
        // GIVEN: A MongoUserEntity is created and stored in the collection.
        MongoUserEntity user = new MongoUserEntity("1", "testUser", "test@example.com", "password");
        collection.insertOne(user);

        // WHEN: The findById() method is called with the ObjectId "1".
        // THEN: The method returns the MongoUserEntity with the matching ObjectId.
        MongoUserEntity foundUser = findById("1");

        // Assert that the returned user has the correct ID.
        assertEquals("1", foundUser.getId());
    }

    @Test
    void insert_insertsUserAndReturnsUser() {
        // GIVEN: A User object is created.
        User user = new User("1", "testUser", "test@example.com", "password");

        // WHEN: The insert() method is called with the User object.
        // THEN: The User object is inserted into the collection, and the method returns the same User object.
        User insertedUser = insert(user);

        // Assert that the returned user has the correct ID.
        assertEquals("1", insertedUser.getId());
    }

    @Test
    void replace_replacesUserByIdAndReturnsUser() {
        // GIVEN: A MongoUserEntity is created and stored in the collection.
        MongoUserEntity user = new MongoUserEntity("1", "testUser",java
        // GIVEN: A MongoUserEntity is created and stored in the collection.
        MongoUserEntity user = new MongoUserEntity("1", "testUser", "test@example.com", "password");
        collection.insertOne(user);

        // WHEN: The replace() method is called with the ObjectId "1" and the same User object.
        // THEN: The MongoUserEntity in the collection is replaced with the same User object, and the method returns the same User object.
        User replacedUser = replace("1", user);

        // Assert that the replaced user has the correct ID.
        assertEquals("1", replacedUser.getId());
    }

    @Test
    void removeById_removesUserByIdAndReturnsAcknowledgement() {
        // GIVEN: A MongoUserEntity is created and stored in the collection.
        MongoUserEntity user = new MongoUserEntity("1", "testUser", "test@example.com", "password");
        collection.insertOne(user);

        // WHEN: The removeById() method is called with the ObjectId "1".
        // THEN: The MongoUserEntity with the matching ObjectId is deleted from the collection, and the method returns true.
        boolean acknowledged = removeById("1");

        // Assert that the deletion was acknowledged.
        assertTrue(acknowledged);
    }

    private String newId() {
        return new ObjectId().toString();
    }

    private MongoUserEntity findByEmail(String email) {
        Bson filter = Filters.and(
            Filters.eq("email", email));

        try {
            return Objects.requireNonNull(this.collection.find(filter).first()).convertTo();
        } catch (Exception ex) {
            throw new InternalServerError("Failed to get detail from database", ex);
        }
    }

    private User insert(User user) {
        try {
            this.collection.insertOne(MongoUserEntity.convertFrom(user));
            return user;
        } catch (Exception ex) {
            throw new InternalServerError("Failed to insert", ex);
        }
    }

    private User replace(String id, User user) {
        MongoUserEntity mongoUserEntity = MongoUserEntity.convertFrom(user);
        Bson filter = Filters.and(
            Filters.eq("_id", mongoUserEntity.getId()));

        try {
            ReplaceOptions opts = new ReplaceOptions().upsert(true);
            UpdateResult result = this.collection.replaceOne(filter, mongoUserEntity, opts);
            if (!result.wasAcknowledged()) {
                throw new RuntimeException("Failed to get Acknowledged on replace operation");
            }
            return user;
        } catch (Exception ex) {
            throw new InternalServerError("Failed to insert", ex);
        }
    }

    private boolean removeById(String id) {
        ObjectId objectId = new ObjectId(id);
        Bson filter = Filters.and(
            Filters.eq("_id", objectId));
        try {
            DeleteResult result = this.collection.deleteOne(filter);
            return result.wasAcknowledged();
        } catch (Exception ex) {
            throw new InternalServerError("Failed to delete", ex);
        }
    }
}
