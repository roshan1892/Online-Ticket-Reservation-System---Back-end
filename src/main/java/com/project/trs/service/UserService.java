package com.project.trs.service;

import com.project.trs.model.User;
import com.project.trs.response.CreateUserResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    CreateUserResponse createUser(User user);

    User getUserByEmail(String email);
}
