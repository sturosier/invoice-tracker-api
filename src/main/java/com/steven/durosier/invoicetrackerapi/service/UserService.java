package com.steven.durosier.invoicetrackerapi.service;

import com.steven.durosier.invoicetrackerapi.entity.User;
import com.steven.durosier.invoicetrackerapi.request.CreateUserRequest;

public interface UserService {
    User createUser(CreateUserRequest request);

    User getUserById(final Long id);

    User getLoggedInUser();
}
