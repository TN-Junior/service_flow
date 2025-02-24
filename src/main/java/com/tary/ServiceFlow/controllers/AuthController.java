package com.tary.ServiceFlow.controllers;

import com.tary.ServiceFlow.security.JwtUtil;
import com.tary.ServiceFlow.entities.Usuario;
import com.tary.ServiceFlow.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public String login(@RequestBody Usuario usuario) {
        Usuario user = usuarioService.buscarPorEmail(usuario.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!passwordEncoder.matches(usuario.getSenha(), user.getSenha())) {
            throw new RuntimeException("Credenciais inválidas");
        }

        return jwtUtil.generateToken(user.getEmail()); // Passa apenas o email do usuário

    }
}
