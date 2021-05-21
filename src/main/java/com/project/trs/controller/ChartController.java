package com.project.trs.controller;

import com.project.trs.response.ChartResponse;
import com.project.trs.service.ChartService;
import com.project.trs.utils.JwtUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
public class ChartController {
    private final ChartService chartService;

    @GetMapping("/fetchChartData")
    @PreAuthorize("hasRole('" + JwtUtils.ROLE_USER + "')")
    ResponseEntity<ChartResponse> getChartData(Authentication authentication) {
        ChartResponse chartResponse = chartService.getChartData(authentication.getName());
        return ResponseEntity.ok(chartResponse);
    }
}
