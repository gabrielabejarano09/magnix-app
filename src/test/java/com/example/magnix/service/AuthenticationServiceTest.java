package com.example.magnix.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.magnix.exception.UserNotFoundException;
import com.example.magnix.model.User;
import com.example.magnix.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {

    @Mock
    private UserRepository userRepository; 

    @Mock
    private PasswordEncoder passwordEncoder; 

    @InjectMocks
    private AuthenticationService authenticationService; 

    @Test
    @DisplayName("Debería autenticar al usuario exitosamente con credenciales correctas")
    void login_shouldSucceed_withCorrectCredentials() {
        // Arrange 
        String email = "jugador1@test.com";
        String rawPassword = "Magnix123";
        String encodedPassword = "encodedPassword123";
        User mockUser = new User(email, encodedPassword, "JUGADOR");

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(mockUser));
        when(passwordEncoder.matches(rawPassword, encodedPassword)).thenReturn(true);

        // Act
        User authenticatedUser = authenticationService.login(email, rawPassword);

        // Assert
        assertNotNull(authenticatedUser);
        assertEquals(email, authenticatedUser.getEmail());
        verify(userRepository).findByEmail(email); 
    }

    @Test
    @DisplayName("Debería lanzar una excepción con contraseña incorrecta")
    void login_shouldThrowException_withIncorrectPassword() {
        // Arrange
        String email = "jugador1@test.com";
        String rawPassword = "password_malo";
        String encodedPassword = "encodedPassword123";
        User mockUser = new User(email, encodedPassword, "JUGADOR");

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(mockUser));
        when(passwordEncoder.matches(rawPassword, encodedPassword)).thenReturn(false); 

        // Act & Assert
        assertThrows(BadCredentialsException.class, () -> {
            authenticationService.login(email, rawPassword);
        }, "Debería lanzar BadCredentialsException");
    }

    @Test
    @DisplayName("Debería lanzar una excepción si el usuario no existe")
    void login_shouldThrowException_whenUserNotFound() {
        // Arrange
        String email = "noexiste@test.com";
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty()); 

        // Act & Assert
        assertThrows(UserNotFoundException.class, () -> {
            authenticationService.login(email, "password");
        }, "Debería lanzar UserNotFoundException");
    }
}