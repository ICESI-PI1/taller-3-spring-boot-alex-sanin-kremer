package com.library.management.persistence.repository.impl;

import com.library.management.persistence.model.User;
import com.library.management.persistence.repository.IUserRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements IUserRepository {

    private static List<User> userEntities;

    static {
        userEntities = new ArrayList<>();
        User user1 = new User("user1", "user1");
        User user2 = new User("user2", "user2");
        userEntities.addAll(Arrays.asList(user1, user2));
    }

    @Override
    public Optional<User> findByName(String name) {
        return userEntities.stream().filter(user -> user.getName().equals(name)).findFirst();
    }

    @Override
    public Optional<User> findByNameAndPassword(final String name, final String password) {
        return userEntities.stream().filter(user -> user.getName().equals(name) && user.getPassword().equals(password))
                .findFirst();
    }


}
