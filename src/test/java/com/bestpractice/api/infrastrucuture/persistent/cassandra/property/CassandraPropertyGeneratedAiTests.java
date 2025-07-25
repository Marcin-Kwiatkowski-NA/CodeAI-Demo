package com.bestpractice.api.infrastrucuture.persistent.cassandra.property;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CassandraPropertyGeneratedAiTests {

    private CassandraProperty cassandraProperty;

    @BeforeEach
    void setUp() {
        cassandraProperty = new CassandraProperty();
    }

    @Test
    void testGetSetHosts() {
        // GIVEN: CassandraProperty object is created.
        // WHEN: The 'hosts' property is set to a non-empty array.
        String[] hosts = {"host1", "host2"};
        cassandraProperty.setHosts(hosts);
        // THEN: The 'hosts' property should be set correctly.
        assertEquals(hosts, cassandraProperty.getHosts());
    }

    @Test
    void testGetSetKeyspace() {
        // GIVEN: CassandraProperty object is created.
        // WHEN: The 'keyspace' property is set to a non-empty string.
        String keyspace = "mykeyspace";
        cassandraProperty.setKeyspace(keyspace);
        // THEN: The 'keyspace' property should be set correctly.
        assertEquals(keyspace, cassandraProperty.getKeyspace());
    }

    @Test
    void testGetSetUser() {
        // GIVEN: CassandraProperty object is created.
        // WHEN: The 'user' property is set to a non-empty string.
        String user = "cassandrauser";
        cassandraProperty.setUser(user);
        // THEN: The 'user' property should be set correctly.
        assertEquals(user, cassandraProperty.getUser());
    }

    @Test
    void testGetSetPassword() {
        // GIVEN: CassandraProperty object is created.
        // WHEN: The 'password' property is set to a non-empty string.
        String password = "cassandra_password";
        cassandraProperty.setPassword(password);
        // THEN: The 'password' property should be set correctly.
        assertEquals(password, cassandraProperty.getPassword());
    }
}

/*
2025-07-25 10:25:56.951 INFO [main] [io.github.adamw7.testing.generator.prompt.UnitTestingPromptProvider.getPromptMessages(UnitTestingPromptProvider.java:36)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.cassandra.property.CassandraPropertyGeneratedAiTests.java}] - Building a prompt for fixing unit tests issue...
2025-07-25 10:25:56.954 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.cassandra.property.CassandraPropertyGeneratedAiTests.java}] - Generating code...
2025-07-25 10:25:56.954 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:107)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.cassandra.property.CassandraPropertyGeneratedAiTests.java}] - Using prompt:

There is an error in the previously generated test class.

>> ERROR:

[ERROR] COMPILATION ERROR : 
[ERROR] /tmp/codeai-test-2441308668156892551/src/test/java/com/bestpractice/api/infrastrucuture/persistent/cassandra/property/CassandraPropertyGeneratedAiTests.java:[14,46] incompatible types: java.lang.Class<com.bestpractice.api.infrastrucuture.persistent.cassandra.property.CassandraPropertyGeneratedAiTests> cannot be converted to java.lang.Class<? extends org.junit.jupiter.api.extension.Extension>
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-compiler-plugin:3.8.1:testCompile (default-testCompile) on project demo-code-ai: Compilation failure
[ERROR] /tmp/codeai-test-2441308668156892551/src/test/java/com/bestpractice/api/infrastrucuture/persistent/cassandra/property/CassandraPropertyGeneratedAiTests.java:[14,46] incompatible types: java.lang.Class<com.bestpractice.api.infrastrucuture.persistent.cassandra.property.CassandraPropertyGeneratedAiTests> cannot be converted to java.lang.Class<? extends org.junit.jupiter.api.extension.Extension>
[ERROR] -> [Help 1]
[ERROR] 
[ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
[ERROR] Re-run Maven using the -X switch to enable full debug logging.
[ERROR] 
[ERROR] For more information about the errors and possible solutions, please read the following articles:
[ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/MojoFailureException


# TASK: Correct the error in the test class.

# Instructions:
1. Focus on the specific error given.
2. Ensure that the corrected code passes and assertions are valid.
3. Keep unrelated parts of the test unchanged.
4. Follow existing project standards, including naming and formatting.

2025-07-25 10:25:56.954 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:111)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.cassandra.property.CassandraPropertyGeneratedAiTests.java}] - Generate code iteration # 1
2025-07-25 10:26:08.805 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.cassandra.property.CassandraPropertyGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 1874, outputTokenCount = 521, totalTokenCount = 2395 }
2025-07-25 10:26:08.806 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.cassandra.property.CassandraPropertyGeneratedAiTests.java}] - Done
2025-07-25 10:26:08.806 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:82)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.cassandra.property.CassandraPropertyGeneratedAiTests.java}] - Generated code:
```java
package com.bestpractice.api.infrastrucuture.persistent.cassandra.property;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(CassandraPropertyGeneratedAiTests.class)
class CassandraPropertyGeneratedAiTests {

    private CassandraProperty cassandraProperty;

    @BeforeEach
    void setUp() {
        cassandraProperty = new CassandraProperty();
    }

    @Test
    void testGetSetHosts() {
        // GIVEN: CassandraProperty object is created.
        // WHEN: The 'hosts' property is set to a non-empty array.
        String[] hosts = {"host1", "host2"};
        cassandraProperty.setHosts(hosts);
        // THEN: The 'hosts' property should be set correctly.
        assertEquals(hosts, cassandraProperty.getHosts());
    }

    @Test
    void testGetSetKeyspace() {
        // GIVEN: CassandraProperty object is created.
        // WHEN: The 'keyspace' property is set to a non-empty string.
        String keyspace = "mykeyspace";
        cassandraProperty.setKeyspace(keyspace);
        // THEN: The 'keyspace' property should be set correctly.
        assertEquals(keyspace, cassandraProperty.getKeyspace());
    }

    @Test
    void testGetSetUser() {
        // GIVEN: CassandraProperty object is created.
        // WHEN: The 'user' property is set to a non-empty string.
        String user = "cassandrauser";
        cassandraProperty.setUser(user);
        // THEN: The 'user' property should be set correctly.
        assertEquals(user, cassandraProperty.getUser());
    }

    @Test
    void testGetSetPassword() {
        // GIVEN: CassandraProperty object is created.
        // WHEN: The 'password' property is set to a non-empty string.
        String password = "cassandra_password";
        cassandraProperty.setPassword(password);
        // THEN: The 'password' property should be set correctly.
        assertEquals(password, cassandraProperty.getPassword());
    }
}
```
2025-07-25 10:26:08.806 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:83)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.cassandra.property.CassandraPropertyGeneratedAiTests.java}] - Refining code...
2025-07-25 10:26:08.807 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:85)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.cassandra.property.CassandraPropertyGeneratedAiTests.java}] - Done
2025-07-25 10:26:14.677 INFO [main] [io.github.adamw7.testing.generator.prompt.UnitTestingPromptProvider.getPromptMessages(UnitTestingPromptProvider.java:33)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.cassandra.property.CassandraPropertyGeneratedAiTests.java}] - Building a prompt with explanation how to fix issue...
2025-07-25 10:26:14.677 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.cassandra.property.CassandraPropertyGeneratedAiTests.java}] - Generating code...
2025-07-25 10:26:14.677 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:107)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.cassandra.property.CassandraPropertyGeneratedAiTests.java}] - Using prompt:

There is this issue with code and how to fix it, provide only code, no other explanations:

Optional[Remove the `@ExtendWith(CassandraPropertyGeneratedAiTests.class)` annotation.
]

In this code:

package com.bestpractice.api.infrastrucuture.persistent.cassandra.property;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(CassandraPropertyGeneratedAiTests.class)
class CassandraPropertyGeneratedAiTests {

    private CassandraProperty cassandraProperty;

    @BeforeEach
    void setUp() {
        cassandraProperty = new CassandraProperty();
    }

    @Test
    void testGetSetHosts() {
        // GIVEN: CassandraProperty object is created.
        // WHEN: The 'hosts' property is set to a non-empty array.
        String[] hosts = {"host1", "host2"};
        cassandraProperty.setHosts(hosts);
        // THEN: The 'hosts' property should be set correctly.
        assertEquals(hosts, cassandraProperty.getHosts());
    }

    @Test
    void testGetSetKeyspace() {
        // GIVEN: CassandraProperty object is created.
        // WHEN: The 'keyspace' property is set to a non-empty string.
        String keyspace = "mykeyspace";
        cassandraProperty.setKeyspace(keyspace);
        // THEN: The 'keyspace' property should be set correctly.
        assertEquals(keyspace, cassandraProperty.getKeyspace());
    }

    @Test
    void testGetSetUser() {
        // GIVEN: CassandraProperty object is created.
        // WHEN: The 'user' property is set to a non-empty string.
        String user = "cassandrauser";
        cassandraProperty.setUser(user);
        // THEN: The 'user' property should be set correctly.
        assertEquals(user, cassandraProperty.getUser());
    }

    @Test
    void testGetSetPassword() {
        // GIVEN: CassandraProperty object is created.
        // WHEN: The 'password' property is set to a non-empty string.
        String password = "cassandra_password";
        cassandraProperty.setPassword(password);
        // THEN: The 'password' property should be set correctly.
        assertEquals(password, cassandraProperty.getPassword());
    }
}


# Instructions:
1. Focus on the specific error given.
2. Ensure that the corrected code passes and assertions are valid.
3. Keep unrelated parts of the test unchanged.
4. Follow existing project standards, including naming and formatting.
5. Give output as a plain text

2025-07-25 10:26:14.677 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:111)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.cassandra.property.CassandraPropertyGeneratedAiTests.java}] - Generate code iteration # 1
2025-07-25 10:26:32.938 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.cassandra.property.CassandraPropertyGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 3084, outputTokenCount = 521, totalTokenCount = 3605 }
2025-07-25 10:26:32.938 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.cassandra.property.CassandraPropertyGeneratedAiTests.java}] - Done
2025-07-25 10:26:32.938 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:82)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.cassandra.property.CassandraPropertyGeneratedAiTests.java}] - Generated code:
```java
package com.bestpractice.api.infrastrucuture.persistent.cassandra.property;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(CassandraPropertyGeneratedAiTests.class)
class CassandraPropertyGeneratedAiTests {

    private CassandraProperty cassandraProperty;

    @BeforeEach
    void setUp() {
        cassandraProperty = new CassandraProperty();
    }

    @Test
    void testGetSetHosts() {
        // GIVEN: CassandraProperty object is created.
        // WHEN: The 'hosts' property is set to a non-empty array.
        String[] hosts = {"host1", "host2"};
        cassandraProperty.setHosts(hosts);
        // THEN: The 'hosts' property should be set correctly.
        assertEquals(hosts, cassandraProperty.getHosts());
    }

    @Test
    void testGetSetKeyspace() {
        // GIVEN: CassandraProperty object is created.
        // WHEN: The 'keyspace' property is set to a non-empty string.
        String keyspace = "mykeyspace";
        cassandraProperty.setKeyspace(keyspace);
        // THEN: The 'keyspace' property should be set correctly.
        assertEquals(keyspace, cassandraProperty.getKeyspace());
    }

    @Test
    void testGetSetUser() {
        // GIVEN: CassandraProperty object is created.
        // WHEN: The 'user' property is set to a non-empty string.
        String user = "cassandrauser";
        cassandraProperty.setUser(user);
        // THEN: The 'user' property should be set correctly.
        assertEquals(user, cassandraProperty.getUser());
    }

    @Test
    void testGetSetPassword() {
        // GIVEN: CassandraProperty object is created.
        // WHEN: The 'password' property is set to a non-empty string.
        String password = "cassandra_password";
        cassandraProperty.setPassword(password);
        // THEN: The 'password' property should be set correctly.
        assertEquals(password, cassandraProperty.getPassword());
    }
}
```
2025-07-25 10:26:32.938 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:83)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.cassandra.property.CassandraPropertyGeneratedAiTests.java}] - Refining code...
2025-07-25 10:26:32.938 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:85)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.cassandra.property.CassandraPropertyGeneratedAiTests.java}] - Done
2025-07-25 10:26:37.670 INFO [main] [io.github.adamw7.testing.generator.prompt.UnitTestingPromptProvider.getPromptMessages(UnitTestingPromptProvider.java:33)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.cassandra.property.CassandraPropertyGeneratedAiTests.java}] - Building a prompt with explanation how to fix issue...
2025-07-25 10:26:37.670 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.cassandra.property.CassandraPropertyGeneratedAiTests.java}] - Generating code...
2025-07-25 10:26:37.670 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:107)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.cassandra.property.CassandraPropertyGeneratedAiTests.java}] - Using prompt:

There is this issue with code and how to fix it, provide only code, no other explanations:

Optional[Remove the `@ExtendWith(CassandraPropertyGeneratedAiTests.class)` annotation.
]

In this code:

package com.bestpractice.api.infrastrucuture.persistent.cassandra.property;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(CassandraPropertyGeneratedAiTests.class)
class CassandraPropertyGeneratedAiTests {

    private CassandraProperty cassandraProperty;

    @BeforeEach
    void setUp() {
        cassandraProperty = new CassandraProperty();
    }

    @Test
    void testGetSetHosts() {
        // GIVEN: CassandraProperty object is created.
        // WHEN: The 'hosts' property is set to a non-empty array.
        String[] hosts = {"host1", "host2"};
        cassandraProperty.setHosts(hosts);
        // THEN: The 'hosts' property should be set correctly.
        assertEquals(hosts, cassandraProperty.getHosts());
    }

    @Test
    void testGetSetKeyspace() {
        // GIVEN: CassandraProperty object is created.
        // WHEN: The 'keyspace' property is set to a non-empty string.
        String keyspace = "mykeyspace";
        cassandraProperty.setKeyspace(keyspace);
        // THEN: The 'keyspace' property should be set correctly.
        assertEquals(keyspace, cassandraProperty.getKeyspace());
    }

    @Test
    void testGetSetUser() {
        // GIVEN: CassandraProperty object is created.
        // WHEN: The 'user' property is set to a non-empty string.
        String user = "cassandrauser";
        cassandraProperty.setUser(user);
        // THEN: The 'user' property should be set correctly.
        assertEquals(user, cassandraProperty.getUser());
    }

    @Test
    void testGetSetPassword() {
        // GIVEN: CassandraProperty object is created.
        // WHEN: The 'password' property is set to a non-empty string.
        String password = "cassandra_password";
        cassandraProperty.setPassword(password);
        // THEN: The 'password' property should be set correctly.
        assertEquals(password, cassandraProperty.getPassword());
    }
}


# Instructions:
1. Focus on the specific error given.
2. Ensure that the corrected code passes and assertions are valid.
3. Keep unrelated parts of the test unchanged.
4. Follow existing project standards, including naming and formatting.
5. Give output as a plain text

2025-07-25 10:26:37.670 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:111)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.cassandra.property.CassandraPropertyGeneratedAiTests.java}] - Generate code iteration # 1
2025-07-25 10:26:50.027 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.cassandra.property.CassandraPropertyGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 4294, outputTokenCount = 521, totalTokenCount = 4815 }
2025-07-25 10:26:50.027 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.cassandra.property.CassandraPropertyGeneratedAiTests.java}] - Done
2025-07-25 10:26:50.027 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:82)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.cassandra.property.CassandraPropertyGeneratedAiTests.java}] - Generated code:
```java
package com.bestpractice.api.infrastrucuture.persistent.cassandra.property;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(CassandraPropertyGeneratedAiTests.class)
class CassandraPropertyGeneratedAiTests {

    private CassandraProperty cassandraProperty;

    @BeforeEach
    void setUp() {
        cassandraProperty = new CassandraProperty();
    }

    @Test
    void testGetSetHosts() {
        // GIVEN: CassandraProperty object is created.
        // WHEN: The 'hosts' property is set to a non-empty array.
        String[] hosts = {"host1", "host2"};
        cassandraProperty.setHosts(hosts);
        // THEN: The 'hosts' property should be set correctly.
        assertEquals(hosts, cassandraProperty.getHosts());
    }

    @Test
    void testGetSetKeyspace() {
        // GIVEN: CassandraProperty object is created.
        // WHEN: The 'keyspace' property is set to a non-empty string.
        String keyspace = "mykeyspace";
        cassandraProperty.setKeyspace(keyspace);
        // THEN: The 'keyspace' property should be set correctly.
        assertEquals(keyspace, cassandraProperty.getKeyspace());
    }

    @Test
    void testGetSetUser() {
        // GIVEN: CassandraProperty object is created.
        // WHEN: The 'user' property is set to a non-empty string.
        String user = "cassandrauser";
        cassandraProperty.setUser(user);
        // THEN: The 'user' property should be set correctly.
        assertEquals(user, cassandraProperty.getUser());
    }

    @Test
    void testGetSetPassword() {
        // GIVEN: CassandraProperty object is created.
        // WHEN: The 'password' property is set to a non-empty string.
        String password = "cassandra_password";
        cassandraProperty.setPassword(password);
        // THEN: The 'password' property should be set correctly.
        assertEquals(password, cassandraProperty.getPassword());
    }
}
```
2025-07-25 10:26:50.027 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:83)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.cassandra.property.CassandraPropertyGeneratedAiTests.java}] - Refining code...
2025-07-25 10:26:50.028 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:85)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.cassandra.property.CassandraPropertyGeneratedAiTests.java}] - Done
2025-07-25 10:26:54.689 INFO [main] [io.github.adamw7.testing.generator.prompt.UnitTestingPromptProvider.getPromptMessages(UnitTestingPromptProvider.java:33)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.cassandra.property.CassandraPropertyGeneratedAiTests.java}] - Building a prompt with explanation how to fix issue...
2025-07-25 10:26:54.689 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:62)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.cassandra.property.CassandraPropertyGeneratedAiTests.java}] - Generating code...
2025-07-25 10:26:54.689 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.printPromptMessages(CodeGenerator.java:107)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.cassandra.property.CassandraPropertyGeneratedAiTests.java}] - Using prompt:

There is this issue with code and how to fix it, provide only code, no other explanations:

Optional[Remove the `@ExtendWith(CassandraPropertyGeneratedAiTests.class)` annotation.
]

In this code:

package com.bestpractice.api.infrastrucuture.persistent.cassandra.property;

import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(CassandraPropertyGeneratedAiTests.class)
class CassandraPropertyGeneratedAiTests {

    private CassandraProperty cassandraProperty;

    @BeforeEach
    void setUp() {
        cassandraProperty = new CassandraProperty();
    }

    @Test
    void testGetSetHosts() {
        // GIVEN: CassandraProperty object is created.
        // WHEN: The 'hosts' property is set to a non-empty array.
        String[] hosts = {"host1", "host2"};
        cassandraProperty.setHosts(hosts);
        // THEN: The 'hosts' property should be set correctly.
        assertEquals(hosts, cassandraProperty.getHosts());
    }

    @Test
    void testGetSetKeyspace() {
        // GIVEN: CassandraProperty object is created.
        // WHEN: The 'keyspace' property is set to a non-empty string.
        String keyspace = "mykeyspace";
        cassandraProperty.setKeyspace(keyspace);
        // THEN: The 'keyspace' property should be set correctly.
        assertEquals(keyspace, cassandraProperty.getKeyspace());
    }

    @Test
    void testGetSetUser() {
        // GIVEN: CassandraProperty object is created.
        // WHEN: The 'user' property is set to a non-empty string.
        String user = "cassandrauser";
        cassandraProperty.setUser(user);
        // THEN: The 'user' property should be set correctly.
        assertEquals(user, cassandraProperty.getUser());
    }

    @Test
    void testGetSetPassword() {
        // GIVEN: CassandraProperty object is created.
        // WHEN: The 'password' property is set to a non-empty string.
        String password = "cassandra_password";
        cassandraProperty.setPassword(password);
        // THEN: The 'password' property should be set correctly.
        assertEquals(password, cassandraProperty.getPassword());
    }
}


# Instructions:
1. Focus on the specific error given.
2. Ensure that the corrected code passes and assertions are valid.
3. Keep unrelated parts of the test unchanged.
4. Follow existing project standards, including naming and formatting.
5. Give output as a plain text

2025-07-25 10:26:54.690 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.generateTillEnd(CodeGenerator.java:111)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.cassandra.property.CassandraPropertyGeneratedAiTests.java}] - Generate code iteration # 1
2025-07-25 10:27:06.834 DEBUG [main] [io.github.adamw7.orchestrator.ai.langchain.LangChainAiService.generate(LangChainAiService.java:45)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.cassandra.property.CassandraPropertyGeneratedAiTests.java}] - TokenUsage { inputTokenCount = 5504, outputTokenCount = 492, totalTokenCount = 5996 }
2025-07-25 10:27:06.835 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:80)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.cassandra.property.CassandraPropertyGeneratedAiTests.java}] - Done
2025-07-25 10:27:06.835 DEBUG [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:82)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.cassandra.property.CassandraPropertyGeneratedAiTests.java}] - Generated code:
```java
package com.bestpractice.api.infrastrucuture.persistent.cassandra.property;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CassandraPropertyGeneratedAiTests {

    private CassandraProperty cassandraProperty;

    @BeforeEach
    void setUp() {
        cassandraProperty = new CassandraProperty();
    }

    @Test
    void testGetSetHosts() {
        // GIVEN: CassandraProperty object is created.
        // WHEN: The 'hosts' property is set to a non-empty array.
        String[] hosts = {"host1", "host2"};
        cassandraProperty.setHosts(hosts);
        // THEN: The 'hosts' property should be set correctly.
        assertEquals(hosts, cassandraProperty.getHosts());
    }

    @Test
    void testGetSetKeyspace() {
        // GIVEN: CassandraProperty object is created.
        // WHEN: The 'keyspace' property is set to a non-empty string.
        String keyspace = "mykeyspace";
        cassandraProperty.setKeyspace(keyspace);
        // THEN: The 'keyspace' property should be set correctly.
        assertEquals(keyspace, cassandraProperty.getKeyspace());
    }

    @Test
    void testGetSetUser() {
        // GIVEN: CassandraProperty object is created.
        // WHEN: The 'user' property is set to a non-empty string.
        String user = "cassandrauser";
        cassandraProperty.setUser(user);
        // THEN: The 'user' property should be set correctly.
        assertEquals(user, cassandraProperty.getUser());
    }

    @Test
    void testGetSetPassword() {
        // GIVEN: CassandraProperty object is created.
        // WHEN: The 'password' property is set to a non-empty string.
        String password = "cassandra_password";
        cassandraProperty.setPassword(password);
        // THEN: The 'password' property should be set correctly.
        assertEquals(password, cassandraProperty.getPassword());
    }
}
```
2025-07-25 10:27:06.835 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:83)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.cassandra.property.CassandraPropertyGeneratedAiTests.java}] - Refining code...
2025-07-25 10:27:06.836 INFO [main] [io.github.adamw7.orchestrator.generator.CodeGenerator.create(CodeGenerator.java:85)] [{conversationName=com.bestpractice.api.infrastrucuture.persistent.cassandra.property.CassandraPropertyGeneratedAiTests.java}] - Done
*/
