package kr.codesqaud.cafe.config;

import kr.codesqaud.cafe.repository.ArticleH2Repository;
import kr.codesqaud.cafe.repository.ArticleRepository;
import kr.codesqaud.cafe.repository.UserH2Repository;
import kr.codesqaud.cafe.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class RepositoryConfig {
    private final DataSource datasource;

    public RepositoryConfig(DataSource datasource) {
        this.datasource = datasource;
    }

    @Bean
    public UserRepository userRepository(){
        return new UserH2Repository(datasource);
    }

    @Bean
    public ArticleRepository articleRepository(){
        return new ArticleH2Repository(datasource);
    }
}
