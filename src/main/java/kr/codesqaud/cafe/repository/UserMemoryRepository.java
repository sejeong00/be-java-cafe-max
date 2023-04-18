package kr.codesqaud.cafe.repository;

import kr.codesqaud.cafe.domain.entity.User;
import kr.codesqaud.cafe.exception.NoSuchUserException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserMemoryRepository implements UserRepository {

    private static final Map<String, User> users = new ConcurrentHashMap<>();

    @Override
    public void save(User user) {
        users.put(user.getUserId(), user);
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }

    @Override
    public Optional<User> findByUserId(String userId) {
        return Optional.ofNullable(users.get(userId));
    }

    @Override
    public void updatePasswordById(final String userId, final String newPassword) {
        User targetUser = findByUserId(userId).orElseThrow(() -> new NoSuchUserException(userId));

        targetUser.updatePassword(newPassword);

        save(targetUser);
    }

}
