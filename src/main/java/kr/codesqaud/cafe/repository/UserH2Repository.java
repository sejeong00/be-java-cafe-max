package kr.codesqaud.cafe.repository;

import kr.codesqaud.cafe.domain.entity.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class UserH2Repository implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserH2Repository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void save(User user) {
        //TODO 중복키 validation
        // org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException: Unique index or primary key violation
        jdbcTemplate.update(
                "INSERT INTO \"user\"(userId, password, name, email) VALUES (?, ?, ?, ?)",
                user.getUserId(),
                user.getPassword(),
                user.getName(),
                user.getEmail()
        );
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("SELECT * FROM \"user\"", userRowMapper());
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        List<User> result = jdbcTemplate.query("SELECT * FROM \"user\" WHERE userId = ?", userRowMapper(), userId);
        return result.stream().findAny();
    }


    private RowMapper<User> userRowMapper() {
        return (rs, rowNum) -> new User(
                rs.getString("userId"),
                rs.getString("password"),
                rs.getString("name"),
                rs.getString("email")
        );
    }
}
