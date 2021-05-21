package com.project.trs.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserResponse {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String role;
    private String createdAt;
}
