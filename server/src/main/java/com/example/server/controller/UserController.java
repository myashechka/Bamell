package com.example.server.controller;


import ch.qos.logback.classic.Logger;
import com.example.server.entity.User;
import com.example.server.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;


@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private static final Logger logger = (Logger) LoggerFactory.getLogger(UserController.class);

    @PostMapping("api/registration")
    public ResponseEntity<?> registerUser(@RequestBody Map<String, String> data) {
        try {
            System.out.println(data);
            User newUser = userService.addUser(data);
            return ResponseEntity.ok(newUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("api/login")
    public ResponseEntity<?> postSignIn(@RequestBody Map<String, String> data, HttpServletRequest req) {
        try {
            return ResponseEntity.ok(userService.authorise(data, req));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("api/auth")
    public ResponseEntity<?> checkAuthentication(Principal principal) {
        try {
            User user = (User) ((Authentication) principal).getPrincipal();
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("api/users")
    public String aaaaa(){
        return "ПЭДРО ПЭДРО ПЭДРО ПЭДРО ПЭЭЭЭ";
    }
    @PostMapping("api/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "fghj";
    }

}
