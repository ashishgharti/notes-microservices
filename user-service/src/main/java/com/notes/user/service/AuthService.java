package com.notes.user.service;

import com.notes.user.dto.AuthResponse;
import com.notes.user.dto.LoginRequest;
import com.notes.user.dto.RegisterRequest;
import com.notes.user.model.Role;
import com.notes.user.model.User;
import com.notes.user.repository.RoleRepository;
import com.notes.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    public AuthResponse register(RegisterRequest request){
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email is already registered.");
        }

        Role userRole= roleRepository.findByName("ROLE_USER")
                .orElseThrow(()-> new RuntimeException("ROLE_USER not found"));

        User user= User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(Collections.singleton(userRole))
                .build();

        userRepository.save(user);

        String token=jwtService.generateToken(user);
        return new AuthResponse(token,user.getName(), user.getEmail());
    }

    public AuthResponse login(LoginRequest request){
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(()->new RuntimeException("Invallid credentials"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new RuntimeException("Invalid credentials");
        }

        String token= jwtService.generateToken(user);
        return new AuthResponse(token, user.getName(), user.getEmail());
    }

}
