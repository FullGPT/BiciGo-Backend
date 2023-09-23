package com.upc.leadyourway.unitTest;


import com.upc.leadyourway.controller.AuthentificationController;
import com.upc.leadyourway.dto.AuthenticationResponse;
import com.upc.leadyourway.dto.LoginRequest;
import com.upc.leadyourway.dto.RegisterRequest;
import com.upc.leadyourway.model.User;
import com.upc.leadyourway.repository.TokenRepository;
import com.upc.leadyourway.repository.UserRepository;
import com.upc.leadyourway.service.AuthService;
import com.upc.leadyourway.service.JwtService;
import com.upc.leadyourway.service.UserService;
import com.upc.leadyourway.service.impl.AuthServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class AuthentificationControllerTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private TokenRepository tokenRepository;
    @Mock
    private JwtService jwtService;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private AuthenticationManager authenticationManager;
    private AuthServiceImp authService;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        authService = new AuthServiceImp(userRepository, tokenRepository, jwtService, passwordEncoder, authenticationManager);
    }
    @Test
    public void testRegisterUser() {
        // Preparar datos de prueba
        RegisterRequest request = new RegisterRequest(/* completar con datos de prueba */);
        User savedUser = new User(/* completar con datos de prueba */);
        String jwtToken = "mocked_jwt_token";
        String refreshToken = "mocked_refresh_token";

        // Configurar comportamiento de los mocks
        when(userRepository.save(any(User.class))).thenReturn(savedUser);
        when(jwtService.generateToken(any(User.class))).thenReturn(jwtToken);
        when(jwtService.generateRefreshToken(any(User.class))).thenReturn(refreshToken);

        // Ejecutar el método a probar
        AuthenticationResponse response = authService.register(request);

        // Verificar el resultado
        assertNotNull(response);
        assertEquals(savedUser.getId(), response.getUser_id());
        assertEquals(jwtToken, response.getAccessToken());
        assertEquals(refreshToken, response.getRefreshToken());

        // Verificar que se llamaron los métodos de los mocks
        verify(userRepository, times(1)).save(any(User.class));
        verify(jwtService, times(1)).generateToken(any(User.class));
        verify(jwtService, times(1)).generateRefreshToken(any(User.class));
    }
    @Test
    public void testLogin() {
        // Preparar datos de prueba
        LoginRequest loginRequest = new LoginRequest("usuario@example.com", "contraseña");
        User user = new User(); // Completa con los datos del usuario necesario para la prueba
        String jwtToken = "mocked_jwt_token";
        String refreshToken = "mocked_refresh_token";

        // Configurar comportamiento de los mocks
        when(userRepository.findByUserEmail(loginRequest.getUserEmail())).thenReturn(user);
        when(jwtService.generateToken(user)).thenReturn(jwtToken);
        when(jwtService.generateRefreshToken(user)).thenReturn(refreshToken);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(null); // Puedes personalizar esto si necesitas simular autenticación

        // Ejecutar el método a probar
        AuthenticationResponse response = authService.login(loginRequest);

        // Verificar el resultado
        assertEquals(user.getId(), response.getUser_id());
        assertEquals(jwtToken, response.getAccessToken());
        assertEquals(refreshToken, response.getRefreshToken());

        // Verificar que se llamaron los métodos de los mocks
        verify(userRepository, times(1)).findByUserEmail(loginRequest.getUserEmail());
        verify(jwtService, times(1)).generateToken(user);
        verify(jwtService, times(1)).generateRefreshToken(user);
        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
    }


}
