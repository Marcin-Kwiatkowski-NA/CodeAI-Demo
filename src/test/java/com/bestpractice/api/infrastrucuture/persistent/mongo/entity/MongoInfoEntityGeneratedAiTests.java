package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;

import com.bestpractice.api.infrastrucuture.entity.Info;
import org.bson.types.ObjectId;

public class MongoInfoEntity {

    private ObjectId id;
    private String title;
    private String description;

    public MongoInfoEntity() {
    }

    public MongoInfoEntity(ObjectId id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ObjectId getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "MongoInfoEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
