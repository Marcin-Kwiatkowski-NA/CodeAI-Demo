package com.bestpractice.api.infrastrucuture.persistent.local;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;

@Override
public Info replace(String id, Info info) {
    Info existingInfo = findById(id);
    if (existingInfo == null) {
        throw new RuntimeException("Data does not exist.");
    }
    replace(existingInfo, info);
    return info;
}
