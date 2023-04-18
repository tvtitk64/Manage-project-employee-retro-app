package com.example.demospringboot.controller;

import com.example.demospringboot.dto.CommonResponseDTO;
import com.example.demospringboot.entity.RefreshToken;
import com.example.demospringboot.exception.TokenRefreshException;
import com.example.demospringboot.payload.request.LoginRequest;
import com.example.demospringboot.payload.request.TokenRefreshRequest;
import com.example.demospringboot.payload.response.JwtResponse;
import com.example.demospringboot.payload.response.TokenRefreshResponse;
import com.example.demospringboot.security.jwt.JwtUtils;
import com.example.demospringboot.security.services.RefreshTokenService;
import com.example.demospringboot.security.services.UserDetailsImpl;
import com.example.demospringboot.service.interfaces.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController

@RequestMapping("/api/auth")
@Slf4j
public class AuthController {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    RefreshTokenService refreshTokenService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
//        log.info("++++++++++++++++++++" + authentication.getPrincipal().getClass().getName());
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String jwt = jwtUtils.generateToken(userDetails);
        List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
                .collect(Collectors.toList());
        String refreshToken = jwtUtils.generateRefreshToken(authentication);
        return ResponseEntity.ok(new JwtResponse(jwt,
                refreshToken,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @GetMapping("/refreshtoken")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null &&authorizationHeader.startsWith("Bearer ")){
            try {
                String refresh_token = authorizationHeader.substring(7);
                String username = jwtUtils.getUsernameFromJwtToken(refresh_token);
                UserDetailsImpl customUserDetails = (UserDetailsImpl) employeeService.loadUserByUsername(username);
                String access_token = jwtUtils.generateToken(customUserDetails);
                List<String> roles = customUserDetails.getAuthorities().stream().map(item -> item.getAuthority())
                        .collect(Collectors.toList());
                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", access_token);
                tokens.put("refresh_token", refresh_token);
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            } catch (Exception e){
                response.setHeader("error", e.getMessage());
                response.setStatus(403);
                Map<String, String> error = new HashMap<>();
                error.put("error_message", e.getMessage());
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        } else throw new RuntimeException("Refresh token is missing");
    }

}
