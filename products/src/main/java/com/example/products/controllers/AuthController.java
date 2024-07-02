package com.example.products.controllers;

import com.example.products.security.AuthenticationReq;
import com.example.products.services.JwtUtilService;
import com.example.products.security.TokenInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    private JwtUtilService jwtUtilService;
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    /* Maneja solicitudes de auth */
    @PostMapping("/authenticate")
    public ResponseEntity<TokenInfo> authenticate(@RequestBody AuthenticationReq authenticationReq) {
        logger.info("Autenticando al usuario {}", authenticationReq.getUser());

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationReq.getUser(), authenticationReq.getClave()));

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationReq.getUser());

        final String jwt = jwtUtilService.generateToken(userDetails);

        return ResponseEntity.ok(new TokenInfo(jwt));
    }

}

