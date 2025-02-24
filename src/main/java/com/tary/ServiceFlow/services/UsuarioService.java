package com.tary.ServiceFlow.services;

import com.tary.ServiceFlow.entities.Usuario;
import com.tary.ServiceFlow.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Criar um usuário com verificação de email duplicado e senha criptografada
    public Usuario criarUsuario(Usuario usuario) {
        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Já existe um usuário com este email.");
        }

        usuario.setSenha(passwordEncoder.encode(usuario.getSenha())); // Criptografa a senha antes de salvar
        return usuarioRepository.save(usuario);
    }

    // Buscar usuário pelo ID
    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    // Listar todos os usuários
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    // Buscar usuário pelo email
    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    // Atualizar um usuário existente (criptografa a senha somente se for alterada)
    public Usuario atualizarUsuario(Long id, Usuario usuarioAtualizado) {
        return usuarioRepository.findById(id).map(usuario -> {
            if (!usuario.getEmail().equals(usuarioAtualizado.getEmail())) {
                if (usuarioRepository.findByEmail(usuarioAtualizado.getEmail()).isPresent()) {
                    throw new IllegalArgumentException("Já existe um usuário com este email.");
                }
                usuario.setEmail(usuarioAtualizado.getEmail());
            }

            if (usuarioAtualizado.getSenha() != null && !usuarioAtualizado.getSenha().isBlank()) {
                usuario.setSenha(passwordEncoder.encode(usuarioAtualizado.getSenha())); // Criptografa apenas se a senha foi alterada
            }

            usuario.setRole(usuarioAtualizado.getRole());
            return usuarioRepository.save(usuario);
        }).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    // Deletar um usuário pelo ID
    public void deletarUsuario(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuário não encontrado");
        }
        usuarioRepository.deleteById(id);
    }
}
