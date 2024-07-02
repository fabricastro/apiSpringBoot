package com.example.products.services;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    /* carga de usuario */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        var usuario = getById(username);

        if (usuario == null) {
            throw new UsernameNotFoundException(username);
        }

        return User
                .withUsername(username)
                .password(usuario.password())
                .roles(usuario.roles().toArray(new String[0]))
                .build();
    }

    public record Usuario(String username, String password, Set<String> roles) {};

    /* busqueda de usuario por id */
    public static Usuario getById(String username) {

        var password = "$2a$10$knW942L0g46h3rW72/Cxtuv0GdGDC4mWyLQAjMNWM7d17sKNcWe9u";

        Usuario castroUser = new Usuario(
                "castro",
                password,
                Set.of("USER")
        );

        var users = List.of(castroUser);

        return users
                .stream()
                .filter(e -> e.username().equals(username))
                .findFirst()
                .orElse(null);
    }

}

