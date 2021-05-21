package com.project.trs.controller;

import com.project.trs.response.LogoutResponse;
import com.project.trs.utils.JwtUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@AllArgsConstructor
@Slf4j
public class LogoutController {

  @GetMapping("/logout")
  @PreAuthorize("hasRole('" + JwtUtils.ROLE_USER + "')")
  ResponseEntity<LogoutResponse> login(HttpServletRequest request) {
    JwtUtils.removeToken(JwtUtils.getToken(request));
    LogoutResponse logoutResponse = new LogoutResponse(JwtUtils.LOGOUT_SUCCESS);
    return ResponseEntity.ok(logoutResponse);
  }
}
