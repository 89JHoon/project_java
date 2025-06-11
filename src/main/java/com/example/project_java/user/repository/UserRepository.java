package com.example.project_java.user.repository;

import com.example.project_java.user.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class UserRepository {
    private final Map<Long, User> userStoreById = new ConcurrentHashMap<>();
    private final Map<String, User> userStoreByUsername = new ConcurrentHashMap<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    public User save(User user) {
        if (user.getId() == null) {
            long newId = idCounter.getAndIncrement();
            user.setId(newId);
        }

        userStoreById.put(user.getId(), user);
        userStoreByUsername.put(user.getUserName(), user);

        return user;
    }

    public Optional<User> findByUsername(String username) {
        return Optional.ofNullable(userStoreByUsername.get(username));
    }

    public boolean existByUsername(String username) {
        return userStoreByUsername.containsKey(username);
    }
}
