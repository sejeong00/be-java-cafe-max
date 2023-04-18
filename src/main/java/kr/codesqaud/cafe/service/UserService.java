package kr.codesqaud.cafe.service;

import kr.codesqaud.cafe.domain.entity.User;
import kr.codesqaud.cafe.dto.UserSignUpRequest;
import kr.codesqaud.cafe.exception.NoSuchUserException;
import kr.codesqaud.cafe.mapper.UserMapper;
import kr.codesqaud.cafe.repository.UserRepository;
import kr.codesqaud.cafe.dto.UserResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void join(UserSignUpRequest userSignUpRequest) {
        userRepository.save(UserMapper.SignUpRequestToEntity(userSignUpRequest));
    }

    public List<UserResponse> findUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::EntityToResponse)
                .collect(Collectors.toList());
    }

    public UserResponse findByUserId(String userId) {
        return userRepository.findByUserId(userId)
                .map(UserMapper::EntityToResponse)
                .orElseThrow(() -> new NoSuchElementException("사용자를 찾을 수 없습니다."));
    }

    public void updatePassword(String userId, String newPassword) {
        User targetUser = userRepository.findByUserId(userId)
                        .orElseThrow(() -> new NoSuchUserException(userId));

        targetUser.updatePassword(newPassword);

        userRepository.save(targetUser);
    }
}
