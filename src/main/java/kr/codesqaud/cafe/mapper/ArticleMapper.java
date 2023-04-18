package kr.codesqaud.cafe.mapper;

import kr.codesqaud.cafe.domain.entity.Article;
import kr.codesqaud.cafe.dto.ArticlePostRequest;
import kr.codesqaud.cafe.dto.ArticleResponse;

public class ArticleMapper {

    public static Article postRequestToEntity(ArticlePostRequest articlePostRequest){
        return new Article(
                articlePostRequest.getWriter(),
                articlePostRequest.getTitle(),
                articlePostRequest.getContents()
        );
    }
    public static ArticleResponse entityToResponse(Article article){
        return new ArticleResponse(
                article.getArticleId(),
                article.getWriter(),
                article.getTitle(),
                article.getContents()
        );
    }
}
