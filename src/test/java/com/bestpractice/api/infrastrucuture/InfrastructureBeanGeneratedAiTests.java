package com.bestpractice.api.infrastrucuture;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;

import static org.mockito.Mockito.mock;
import com.bestpractice.api.infrastrucuture.InfrastructureBean;
import com.bestpractice.api.infrastrucuture.cache.redis.RedisProperty;
import com.bestpractice.api.infrastrucuture.persistent.cassandra.CassandraInfoPersistentRepository;
import com.bestpractice.api.infrastrucuture.persistent.cassandra.CassandraProperty;
import com.bestpractice.api.infrastrucuture.persistent.local.LocalInfoPersistentRepository;
import com.bestpractice.api.infrastrucuture.persistent.local.LocalUserPersistentRepository;
import com.bestpractice.api.infrastrucuture.persistent.mongo.MongoInfoPersistentRepository;
import com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoInfoEntity;
import com.bestpractice.api.infrastrucuture.entity.Info;
import com.bestpractice.api.common.exception.InternalServerError;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit5.PowerMockExtension;

import java.net.InetSocketAddress;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.*;


import com.datastax.oss.driver.api.core.CqlIdentifier;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.CqlSessionBuilder;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ReplaceOptions;
import org.bson.conversions.Bson;

@ExtendWith({MockitoExtension.class, PowerMockExtension.class})
@PrepareForTest({CqlSession.class, MongoClients.class})
class InfrastructureBeanGeneratedAiTests {

    @BeforeEach
    void reset() {
        // No shared state to reset
    }

    @Test
    void testLocalDbRepositoryBeansCreation() {
        // GIVEN
        InfrastructureBean.LocalDbRepository repo = new InfrastructureBean.LocalDbRepository();

        // WHEN
        var userRepo = repo.userRepository();
        var infoRepo = repo.infoRepository();

        // THEN
        assertThat(userRepo).isNotNull().isInstanceOf(LocalUserPersistentRepository.class);
        assertThat(infoRepo).isNotNull().isInstanceOf(LocalInfoPersistentRepository.class);
    }

    @Test
    void testRdbmsDbRepositoryDataSourceConfiguration() throws Exception {
        // GIVEN
        InfrastructureBean.RdbmsDbRepository repo = new InfrastructureBean.RdbmsDbRepository();
        java.lang.reflect.Field urlField = InfrastructureBean.RdbmsDbRepository.class.getDeclaredField("url");
        urlField.setAccessible(true);
        urlField.set(repo, "jdbc:h2:mem:testdb");
        java.lang.reflect.Field userField = InfrastructureBean.RdbmsDbRepository.class.getDeclaredField("username");
        userField.setAccessible(true);
        userField.set(repo, "sa");
        java.lang.reflect.Field passField = InfrastructureBean.RdbmsDbRepository.class.getDeclaredField("password");
        passField.setAccessible(true);
        passField.set(repo, "");
        java.lang.reflect.Field driverField = InfrastructureBean.RdbmsDbRepository.class.getDeclaredField("driverClassName");
        driverField.setAccessible(true);
        driverField.set(repo, "org.h2.Driver");

        // WHEN
        var dataSource = repo.dataSource();

        // THEN
        assertThat(dataSource).isNotNull();
        assertThat(dataSource.getUrl()).isEqualTo("jdbc:h2:mem:testdb");
        assertThat(dataSource.getUsername()).isEqualTo("sa");
        assertThat(dataSource.getPassword()).isEqualTo("");
        assertThat(dataSource.getDriverClassName()).isEqualTo("org.h2.Driver");
    }

    @Test
    void testCassandraDbRepositoryCqlSession() throws Exception {
        // GIVEN
        CassandraProperty mockCassandraProperty = mock(CassandraProperty.class);
        when(mockCassandraProperty.getHosts()).thenReturn(Collections.singletonList("127.0.0.1:9042"));
        when(mockCassandraProperty.getKeyspace()).thenReturn("test_keyspace");
        InfrastructureBean.CassandraDbRepository repo = new InfrastructureBean.CassandraDbRepository(mockCassandraProperty);

        CqlSessionBuilder mockBuilder = mock(CqlSessionBuilder.class);
        CqlSession mockSession = mock(CqlSession.class);
        when(CqlSession.builder()).thenReturn(mockBuilder);
        when(mockBuilder.addContactPoint(any(InetSocketAddress.class))).thenReturn(mockBuilder);
        when(mockBuilder.withLocalDatacenter(anyString())).thenReturn(mockBuilder);
        when(mockBuilder.withKeyspace(any(CqlIdentifier.class))).thenReturn(mockBuilder);
        when(mockBuilder.build()).thenReturn(mockSession);

        // WHEN
        var session = repo.cqlSession();

        // THEN
        assertThat(session).isSameAs(mockSession);
        ArgumentCaptor<InetSocketAddress> addressCaptor = ArgumentCaptor.forClass(InetSocketAddress.class);
        verify(mockBuilder).addContactPoint(addressCaptor.capture());
        InetSocketAddress captured = addressCaptor.getValue();
        assertThat(captured.getHostString()).isEqualTo("127.0.0.1");
        assertThat(captured.getPort()).isEqualTo(9042);
        verify(mockBuilder).withLocalDatacenter("dc01");
        ArgumentCaptor<CqlIdentifier> keyspaceCaptor = ArgumentCaptor.forClass(CqlIdentifier.class);
        verify(mockBuilder).withKeyspace(keyspaceCaptor.capture());
        assertThat(keyspaceCaptor.getValue().asCql()).isEqualTo("test_keyspace");
    }

    @Test
    void testMongoDbRepositoryMongoClientCreation() {
        // GIVEN
        MongoProperty mockMongoProperty = mock(MongoProperty.class);
        when(mockMongoProperty.getHost()).thenReturn("localhost");
        when(mockMongoProperty.getPort()).thenReturn(27017);
        when(mockMongoProperty.getUser()).thenReturn("user");
        when(mockMongoProperty.getAuthDatabase()).thenReturn("admin");
        when(mockMongoProperty.getPassword()).thenReturn("pass");
        when(mockMongoProperty.getPlatformDatabase()).thenReturn("test_db");
        InfrastructureBean.MongoDbRepository repo = new InfrastructureBean.MongoDbRepository(mockMongoProperty);

        MongoClient mockClient = mock(MongoClient.class);
        when(MongoClients.create(any(MongoClientSettings.class))).thenReturn(mockClient);

        // WHEN
        var client = repo.mongoClient();

        // THEN
        assertThat(client).isSameAs(mockClient);
        verify(MongoClients.class).create(any(MongoClientSettings.class));
    }

    @Test
    void testMongoDbRepositoryMongoDatabaseBean() {
        // GIVEN
        MongoProperty mockMongoProperty = mock(MongoProperty.class);
        when(mockMongoProperty.getPlatformDatabase()).thenReturn("test_db");
        InfrastructureBean.MongoDbRepository repo = new InfrastructureBean.MongoDbRepository(mockMongoProperty);

        MongoClient mockClient = mock(MongoClient.class);
        MongoDatabase mockDatabase = mock(MongoDatabase.class);
        when(MongoClients.create(any(MongoClientSettings.class))).thenReturn(mockClient);
        when(mockClient.getDatabase("test_db")).thenReturn(mockDatabase);

        // WHEN
        var database = repo.mongoDatabase();

        // THEN
        assertThat(database).isSameAs(mockDatabase);
        verify(mockClient).getDatabase("test_db");
    }

    @Test
    void testMongoInfoPersistentRepositoryInsertAndFind() {
        // GIVEN
        MongoClient mockClient = mock(MongoClient.class);
        MongoDatabase mockDatabase = mock(MongoDatabase.class);
        MongoCollection<MongoInfoEntity> mockCollection = mock(MongoCollection.class);
        when(mockDatabase.getCollection(anyString(), eq(MongoInfoEntity.class))).thenReturn(mockCollection);
        MongoInfoPersistentRepository repo = new MongoInfoPersistentRepository(mockClient, mockDatabase);

        Info info = new Info();
        try {
            java.lang.reflect.Field idField = Info.class.getDeclaredField("id");
            idField.setAccessible(true);
            idField.set(info, "1");
            java.lang.reflect.Field nameField = Info.class.getDeclaredField("name");
            nameField.setAccessible(true);
            nameField.set(info, "Test");
        } catch (Exception e) {
            // ignore
        }

        // WHEN
        repo.insert(info);

        // THEN
        ArgumentCaptor<MongoInfoEntity> captor = ArgumentCaptor.forClass(MongoInfoEntity.class);
        verify(mockCollection).insertOne(captor.capture());
        MongoInfoEntity captured = captor.getValue();
        assertThat(captured.getId()).isEqualTo("1");
        assertThat(captured.getName()).isEqualTo("Test");
    }

    @Test
    void testMongoInfoPersistentRepositoryFindAll() {
        // GIVEN
        MongoClient mockClient = mock(MongoClient.class);
        MongoDatabase mockDatabase = mock(MongoDatabase.class);
        MongoCollection<MongoInfoEntity> mockCollection = mock(MongoCollection.class);
        FindIterable<MongoInfoEntity> mockFindIterable = mock(FindIterable.class);
        MongoCursor<MongoInfoEntity> mockCursor = mock(MongoCursor.class);
        MongoInfoEntity entity = new MongoInfoEntity();
        try {
            java.lang.reflect.Field idField = MongoInfoEntity.class.getDeclaredField("id");
            idField.setAccessible(true);
            idField.set(entity, "1");
            java.lang.reflect.Field nameField = MongoInfoEntity.class.getDeclaredField("name");
            nameField.setAccessible(true);
            nameField.set(entity, "Test");
        } catch (Exception e) {
            // ignore
        }
        when(mockDatabase.getCollection(anyString(), eq(MongoInfoEntity.class))).thenReturn(mockCollection);
        when(mockCollection.find()).thenReturn(mockFindIterable);
        when(mockFindIterable.iterator()).thenReturn(mockCursor);
        when(mockCursor.hasNext()).thenReturn(true, false);
        when(mockCursor.next()).thenReturn(entity);
        MongoInfoPersistentRepository repo = new MongoInfoPersistentRepository(mockClient, mockDatabase);

        // WHEN
        List<Info> result = repo.findAll();

        // THEN
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getId()).isEqualTo("1");
        assertThat(result.get(0).getName()).isEqualTo("Test");
    }

    @Test
    void testMongoInfoPersistentRepositoryFindByIdThrowsInternalServerError() {
        // GIVEN
        MongoClient mockClient = mock(MongoClient.class);
        MongoDatabase mockDatabase = mock(MongoDatabase.class);
        MongoCollection<MongoInfoEntity> mockCollection = mock(MongoCollection.class);
        when(mockDatabase.getCollection(anyString(), eq(MongoInfoEntity.class))).thenReturn(mockCollection);
        when(mockCollection.find(any(Bson.class)).first()).thenThrow(new RuntimeException("db error"));
        MongoInfoPersistentRepository repo = new MongoInfoPersistentRepository(mockClient, mockDatabase);

        // WHEN & THEN
        assertThatThrownBy(() -> repo.findById("1")).isInstanceOf(InternalServerError.class);
    }
}
