package com.bestpractice.api.infrastrucuture.persistent.mongo;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;

Info info = new Info();
        info.setId(entity.getId().toString());
        info.setTitle(entity.getTitle());
        info.setDescription(entity.getDescription());

        // Act
        Info replacedInfo = repository.replace("testId", info);

        // Assert
        assert replacedInfo != null;
        assert replacedInfo.getId().equals(info.getId());
        assert replacedInfo.getTitle().equals(info.getTitle());
        assert replacedInfo.getDescription().equals(info.getDescription());

        // Verify that the data was updated in the database
        MongoInfoEntity retrievedEntity = collection.find(Filters.eq("_id", new ObjectId(replacedInfo.getId())));
        assert retrievedEntity != null;
        assert retrievedEntity.getTitle().equals(replacedInfo.getTitle());
        assert retrievedEntity.getDescription().equals(replacedInfo.getDescription());
    }

    @Test
    void removeById() {
        // Arrange
        MongoInfoEntity entity = new MongoInfoEntity(new ObjectId(), "Title", "Description");
        collection.insertOne(entity);

        // Act
        boolean removed = repository.removeById("testId");

        // Assert
        assert removed == true;
        assert !collection.find(Filters.eq("_id", new ObjectId("testId"))).first() != null;
    }
}
