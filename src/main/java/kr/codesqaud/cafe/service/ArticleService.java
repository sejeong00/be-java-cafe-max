package kr.codesqaud.cafe.service;

import kr.codesqaud.cafe.dto.ArticlePostRequest;
import kr.codesqaud.cafe.domain.entity.Article;
import kr.codesqaud.cafe.dto.ArticleResponse;
import kr.codesqaud.cafe.mapper.ArticleMapper;
import kr.codesqaud.cafe.repository.ArticleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
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
                .orElseThrow(() -> new NoSuchElementException("게시글을 찾을 수 없습니다."));
    }

    public void post(ArticlePostRequest articlePostRequest) {
        Article article = new Article(
                articlePostRequest.getWriter(),
                articlePostRequest.getTitle(),
                articlePostRequest.getContents()
        );

        articleRepository.save(article);
    }

}

