package com.dev.kayo.JiujitsuAcademy.service;

import com.dev.kayo.JiujitsuAcademy.entity.User;
import com.dev.kayo.JiujitsuAcademy.exceptions.CredenciaisInvalidasException;
import com.dev.kayo.JiujitsuAcademy.repository.UserRepository;
import com.dev.kayo.JiujitsuAcademy.request.LoginRequest;
import com.dev.kayo.JiujitsuAcademy.request.RegisterRequest;
import com.dev.kayo.JiujitsuAcademy.response.LoginResponse;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, @Lazy AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found with email" + email));

    }


    public void register(RegisterRequest request) {

        if (userRepository.count() > 0) {
            throw new UsernameNotFoundException("Você não pode duplicar usuarios");
        }

        User users = User.builder()
                .name(request.nome())
                .email(request.email())
                .password(passwordEncoder.encode(request.senha()))
                .build();

        userRepository.save(users);
    }

    public LoginResponse login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.senha())
        );

        User users = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new CredenciaisInvalidasException("Credenciais invalidas."));

        String token = jwtService.generateToken(users);

        return new LoginResponse(users.getName(),token);

    }


}
