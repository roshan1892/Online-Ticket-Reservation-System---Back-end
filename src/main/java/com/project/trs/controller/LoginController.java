package com.project.trs.controller;

import com.project.trs.form.LoginForm;
import com.project.trs.response.LoginResponse;
import com.project.trs.utils.JwtUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@Slf4j
public class LoginController {

  private final JwtUtils jwtUtils;
  private final AuthenticationManager authenticationManager;

  @PostMapping("/login")
  ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginForm loginForm) {
    LoginResponse loginResponse = null;
    try {
      UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginForm.getEmail(), loginForm.getPassword());
      Authentication authentication = authenticationManager.authenticate(authenticationToken);
      String role = authentication.getAuthorities().toString();
      role = role.substring(1, role.indexOf("]"));
      String token = jwtUtils.generateJwtToken(authentication, loginForm.isRememberMe());
      jwtUtils.addToken(token);
      loginResponse = new LoginResponse(token, role, JwtUtils.LOGIN_SUCCESS);
    } catch (AuthenticationException e) {
      throw e;
    }

    return ResponseEntity.ok(loginResponse);
  }
}
