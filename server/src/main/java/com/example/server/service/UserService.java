package com.example.server.service;


import com.example.server.entity.User;
import com.example.server.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    public User addUser(Map<String, String> data) throws Exception {
        if (userRepository.existsByUsername(data.get("username"))){
            throw new Exception("Username already exists");
        }
        User newUser = new User();
        newUser.setName(data.get("name"));
        newUser.setSurname(data.get("surname"));
        newUser.setPhone(data.get("phone"));
        newUser.setUsername(data.get("username"));
        newUser.setPassword(passwordEncoder.encode(data.get("password")));
        return userRepository.save(newUser);
    }

    public Optional<User> getUser(Integer userId){
        return userRepository.findById(userId);
    }


    public Boolean authorise(Map<String, String> data, HttpServletRequest req) {
        UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(data.get("username"),
                data.get("password"));

        Authentication auth = authenticationManager.authenticate(authReq);
        if(!auth.isAuthenticated()) {
            return false;
        }

        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(auth);
        HttpSession session = req.getSession(true);
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, sc);

        return true;
    }
}

