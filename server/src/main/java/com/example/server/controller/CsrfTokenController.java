package com.example.server.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CsrfTokenController {
    @GetMapping("api/csrf-token")
    public CsrfToken getCsrfToken(CsrfToken token) {
        return token;
    }
}