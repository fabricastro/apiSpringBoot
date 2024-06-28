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

    /********** CARGA DE USUARIO POR NOMBRE DE USUARIO **********/
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        var usuario = getById(username); // Busca el usuario por nombre de usuario.

        if (usuario == null) {
            throw new UsernameNotFoundException(username); // Si el usuario no se encuentra, lanza una excepción.
        }

        return User // Construye y retorna un objeto UserDetails con los datos del usuario.
                .withUsername(username)
                .password(usuario.password())
                .roles(usuario.roles().toArray(new String[0]))
                .build();
    }

    public record Usuario(String username, String password, Set<String> roles) {};

    /********** BÚSQUEDA DE USUARIO POR ID **********/
    public static Usuario getById(String username) {

        var password = "$2a$10$knW942L0g46h3rW72/Cxtuv0GdGDC4mWyLQAjMNWM7d17sKNcWe9u"; // Contraseña encriptada del usuario.

        Usuario castroUser = new Usuario(
                "castro",
                password,
                Set.of("USER") // Crea un usuario de ejemplo.
        );

        var users = List.of(castroUser); // Lista de users.

        return users  // Busca y retorna el usuario que coincide con el nombre de usuario.
                .stream()
                .filter(e -> e.username().equals(username))
                .findFirst()
                .orElse(null);
    }

}

