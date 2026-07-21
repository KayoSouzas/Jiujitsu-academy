package com.dev.kayo.JiujitsuAcademy.service;

import org.springframework.security.core.userdetails.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

class JwtServiceTest {

    private JwtService jwtService;

    private UserDetails userDetails;

    @BeforeEach
    void setUp() {
        jwtService = new JwtService();

        // Como secretKey e expirationMs vêm de @Value (só existem com contexto Spring),
        // aqui setamos "na mão" via reflection, já que é um teste unitário puro.
        ReflectionTestUtils.setField(jwtService, "secretKey", "8f3a9c2e1b7d4f6a0e5c8b1d3f7a9e2c4b6d8f0a1c3e5b7d9f1a3c5e7b9d1f3a");
        ReflectionTestUtils.setField(jwtService, "expirationsMs", 86400000L); // 24h

        userDetails = new User("kayo@email.com", "senha123", java.util.List.of());
    }

    @Test
    void deveGerarTokenValido() {
        String token = jwtService.generateToken(userDetails);

        assertNotNull(token);
        assertFalse(token.isBlank());
    }

    @Test
    void deveExtrairUsernameCorretoDoToken() {
        String token = jwtService.generateToken(userDetails);

        String username = jwtService.extractUsername(token);

        assertEquals("kayo@email.com", username);
    }

    @Test
    void deveValidarTokenComUsuarioCorreto() {
        String token = jwtService.generateToken(userDetails);

        boolean valido = jwtService.isTokenValid(token, userDetails);

        assertTrue(valido);
    }

    @Test
    void naoDeveValidarTokenComUsuarioDiferente() {
        String token = jwtService.generateToken(userDetails);
        UserDetails outroUsuario = new User("outro@email.com", "outrasenha", java.util.List.of());

        boolean valido = jwtService.isTokenValid(token, outroUsuario);

        assertFalse(valido);
    }

    @Test
    void naoDeveValidarTokenExpirado() {
        // Seta expiração pra -1ms, ou seja, o token já nasce expirado
        ReflectionTestUtils.setField(jwtService, "expirationsMs", -1L);
        String token = jwtService.generateToken(userDetails);

        assertThrows(io.jsonwebtoken.ExpiredJwtException.class,
                () -> jwtService.isTokenValid(token, userDetails));
    }

}