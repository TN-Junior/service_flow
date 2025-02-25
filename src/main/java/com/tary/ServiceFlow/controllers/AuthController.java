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

        return jwtUtil.generateToken(user.getEmail()); 

    }

    @PostMapping("/register")
    public String register(@RequestBody Usuario usuario) {
        // Verifica se o usuário já existe
        if (usuarioService.buscarPorEmail(usuario.getEmail()).isPresent()) {
            throw new RuntimeException("E-mail já cadastrado");
        }


        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));


        usuarioService.salvarUsuario(usuario);


        return jwtUtil.generateToken(usuario.getEmail());
    }
}
