package kr.codesqaud.cafe.exception;

public class NoSuchUserException extends RuntimeException{
    final static String message = "%s는 존재하지 않는 사용자입니다.";

    public NoSuchUserException(String userId) {
        super(String.format(message, userId));
    }
}
