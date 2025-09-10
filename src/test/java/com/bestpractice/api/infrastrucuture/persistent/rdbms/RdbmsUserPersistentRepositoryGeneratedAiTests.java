package com.bestpractice.api.infrastrucuture.persistent.rdbms;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.infrastrucuture.entity.User;
import com.bestpractice.api.infrastrucuture.persistent.UserPersistentRepository;

import java.util.UUID;
import org.springframework.jdbc.core.JdbcTemplate;

public class RdbmsUserPersistentRepository implements UserPersistentRepository {
  private final JdbcTemplate jdbcTemplate;

  public RdbmsUserPersistentRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public String newId() {
    return UUID.randomUUID().toString();
  }

  @Override
  public User findByEmail(String email) {
    return null;
  }

  @Override
  public User findById(String id) {
    return null;
  }

  @Override
  public User insert(User user) {
    String sql = "INSERT INTO users (id, username, email, password) VALUES (?, ?, ?, ?)";

    try {
      jdbcTemplate.update(
          sql,
          user.getId(),
          user.getUsername(),
          user.getEmail(),
          user.getPassword()
      );
    } catch (Exception e) {
      throw new Conflict(e);
    }
    return user;
  }

  @Override
  public User replace(String id, User user) {
    String updateSql = "UPDATE users SET username = ?, email = ?, password = ? WHERE id = ?";
    jdbcTemplate.update(
        updateSql,
        user.getUsername(),
        user.getEmail(),
        user.getPassword(),
        id
    );
    return user;
  }

  @Override
  public boolean removeById(String id) {
    String deleteSql = "DELETE FROM users WHERE id = ?";

    try {
      jdbcTemplate.update(deleteSql, id);
      return true;
    } catch (Exception ignored) {
      return false;
    }
  }
}

package com.bestpractice.api.common.exception;

public class Conflict extends RuntimeException {
  public Conflict() {
    super();
  }

  public Conflict(String msg) {
    super(msg);
  }

  public Conflict(Throwable cause) {
    super(cause);
  }

  public Conflict(String msg, Throwable cause) {
    super(msg, cause);
  }
}

package com.bestpractice.api.infrastrucuture.entity;

import java.io.Serializable;

public class User extends SharedData implements Serializable {

    private String  id;

    private String username;

    private String email;

    private String password;

    public User() {
    }

    public User(String id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

package com.bestpractice.api.infrastrucuture.persistent;

import org.springframework.context.annotation.Profile;

@Profile("!test")
public interface UserPersistentRepository {
    String newId();

    User findByEmail(String email);

    User findById(String id);

    User insert(User user);

    User replace(String id, User user);

    boolean removeById(String id);
}
