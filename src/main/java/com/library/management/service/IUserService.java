package com.library.management.service;

import com.library.management.persistence.model.LoginRequest;
import com.library.management.persistence.model.LoginResponse;

public interface IUserService {

    LoginResponse getUserByNameAndPassword(final LoginRequest loginRequest) throws Exception;
}
