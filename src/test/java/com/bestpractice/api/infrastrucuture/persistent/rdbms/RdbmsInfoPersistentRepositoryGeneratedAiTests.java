package com.bestpractice.api.infrastrucuture.persistent.rdbms;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.springframework.jdbc.core.JdbcTemplate;

import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.infrastrucuture.entity.Info;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.UUID;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class RdbmsInfoPersistentRepository implements InfoPersistentRepository {
  private final JdbcTemplate jdbcTemplate;

  public RdbmsInfoPersistentRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public String newId() {
    return UUID.randomUUID().toString();
  }

  @Override
  public List<Info> findAll() {
    return jdbcTemplate.query("SELECT * FROM info", new BeanPropertyRowMapper<>(Info.class));
  }

  @Override
  public Info save(Info info) {
    String id = newId();
    String query = "INSERT INTO info (id, name) VALUES (?, ?)";
    jdbcTemplate.update(query, new Object[]{id, info.getName()});
    return info;
  }

  @Override
  public Info findById(String id) {
    String query = "SELECT * FROM info WHERE id = ?";
    return jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<>(Info.class), id);
  }

  @Override
  public void deleteById(String id) {
    String query = "DELETE FROM info WHERE id = ?";
    jdbcTemplate.update(query, id);
  }

  @Override
  public Info update(Info info) {
    String query = "UPDATE info SET name = ? WHERE id = ?";
    jdbcTemplate.update(query, new Object[]{info.getName(), info.getId()});
    return info;
  }
}
