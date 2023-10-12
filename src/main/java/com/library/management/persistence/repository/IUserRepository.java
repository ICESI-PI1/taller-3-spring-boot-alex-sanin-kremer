package com.library.management.persistence.repository;

import com.library.management.persistence.model.User;

import java.util.Optional;

public interface IUserRepository {

    Optional<User> findByName(final String name);

    Optional<User> findByNameAndPassword(final String name, final String password);

}
