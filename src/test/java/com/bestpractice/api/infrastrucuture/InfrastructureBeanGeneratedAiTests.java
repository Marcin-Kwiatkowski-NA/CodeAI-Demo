package com.bestpractice.api.infrastrucuture;

        // GIVEN a new InfrastructureBean instance

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
        InfrastructureBean infrastructureBean = new InfrastructureBean();
        // WHEN the newId() method is called
        String id = infrastructureBean.newId();
        // THEN the id should be a valid UUID string
        assert id != null;
        assert !id.isEmpty();
    }
}
