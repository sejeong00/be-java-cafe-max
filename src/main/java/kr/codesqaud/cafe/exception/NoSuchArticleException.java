package kr.codesqaud.cafe.exception;

public class NoSuchArticleException extends RuntimeException{
    final static String message = "%d번 게시글은 존재하지 않습니다.";

    public NoSuchArticleException(long articleId) {
        super(String.format(message, articleId));
    }
}
