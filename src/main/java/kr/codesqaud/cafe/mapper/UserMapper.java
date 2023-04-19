package kr.codesqaud.cafe.mapper;

import kr.codesqaud.cafe.domain.entity.User;
import kr.codesqaud.cafe.dto.UserResponse;
import kr.codesqaud.cafe.dto.UserSignUpRequest;

public class UserMapper {
    public static User SignUpRequestToEntity(UserSignUpRequest userSignUpRequest){
        return new User(
                userSignUpRequest.getUserId(),
                userSignUpRequest.getPassword(),
                userSignUpRequest.getName(),
                userSignUpRequest.getEmail()
        );
    }

    public static UserResponse EntityToResponse(User user){
        return new UserResponse(
                user.getUserId().trim(),
                user.getPassword().trim(),
                user.getName(),
                user.getEmail()
        );
    }
}
