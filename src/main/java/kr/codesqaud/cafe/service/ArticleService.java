package kr.codesqaud.cafe.service;

import kr.codesqaud.cafe.dto.ArticlePostRequest;
import kr.codesqaud.cafe.dto.ArticleResponse;
import kr.codesqaud.cafe.exception.NoSuchArticleException;
import kr.codesqaud.cafe.mapper.ArticleMapper;
import kr.codesqaud.cafe.repository.ArticleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public void post(ArticlePostRequest articlePostRequest) {
        articleRepository.save(ArticleMapper.postRequestToEntity(articlePostRequest));
    }

    public List<ArticleResponse> findArticles() {
        return articleRepository.findAll()
                .stream()
                .map(ArticleMapper::entityToResponse)
                .collect(Collectors.toList());

    }

    public ArticleResponse findByArticleId(long articleId) {
         return articleRepository.findByArticleId(articleId)
                .map(ArticleMapper::entityToResponse)
                .orElseThrow(() -> new NoSuchArticleException(articleId));
    }

}

