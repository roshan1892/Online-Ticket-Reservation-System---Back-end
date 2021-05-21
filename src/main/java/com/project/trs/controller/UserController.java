package com.project.trs.controller;

import com.project.trs.form.CreateUserForm;
import com.project.trs.model.User;
import com.project.trs.response.CreateUserResponse;
import com.project.trs.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.Instant;

@RestController
@AllArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping("/createUser")
    ResponseEntity<CreateUserResponse> createUser(@Valid @RequestBody CreateUserForm createUserForm) {

        log.info("Creating Ticket.");
        log.debug("{}", createUserForm);

        CreateUserResponse response = userService.createUser(User.builder()
                .firstName(createUserForm.getFirstName())
                .lastName(createUserForm.getLastName())
                .email(createUserForm.getEmail())
                .password(createUserForm.getPassword())
                .createdAt(Instant.now())
                .role(createUserForm.getRole())
                .build());

        return ResponseEntity.ok(response);
    }
}
