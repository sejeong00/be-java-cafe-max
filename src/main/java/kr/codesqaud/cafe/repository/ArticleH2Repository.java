package kr.codesqaud.cafe.repository;

import kr.codesqaud.cafe.domain.entity.Article;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

@Repository
public class ArticleH2Repository implements ArticleRepository {
    private final JdbcTemplate jdbcTemplate;

    public ArticleH2Repository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void save(Article article) {
        //TODO 중복키 validation
        // org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException: Unique index or primary key violation
        jdbcTemplate.update(
                "INSERT INTO article(writer, title, contents) VALUES (?, ?, ?)",
                article.getWriter(),
                article.getTitle(),
                article.getContents()
        );
    }

    @Override
    public List<Article> findAll() {
        return jdbcTemplate.query("SELECT * FROM article", articleRowMapper());
    }

    @Override
    public Optional<Article> findByArticleId(long articleId) {
        List<Article> result = jdbcTemplate.query(
                "SELECT * FROM article WHERE articleId = ?",
                articleRowMapper(),
                articleId);
        return result.stream().findAny();
    }

    private RowMapper<Article> articleRowMapper() {
        return (rs, rowNum) -> new Article(
                rs.getLong("articleId"),
                rs.getString("writer"),
                rs.getString("title"),
                rs.getString("contents")
        );
    }
}
