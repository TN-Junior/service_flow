package com.tary.ServiceFlow.services;

import com.tary.ServiceFlow.entities.Usuario;
import com.tary.ServiceFlow.repositories.UsuarioRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // cria um/o usuario
    public Usuario criarUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    // busca pelo id do usuario
    public Optional<Usuario> buscarPorId(Long id){
        return usuarioRepository.findById(id);
    }

    // lista geral
    public List<Usuario> listarUsuarios(){
        return usuarioRepository.findAll();
    }

    // procura o usuario pelo email
    public Optional<Usuario> buscarPorEmail(String email){
        return usuarioRepository.findByEmail(email);
    }

    // atualiza um usuario cadastrado
    public Usuario atualizarUsuario(Long id, Usuario usuarioAtualizado){
        return usuarioRepository.findById(id).map(usuario -> {
            usuario.setEmail(usuarioAtualizado.getEmail());
            usuario.setSenha(usuarioAtualizado.getSenha());
            usuario.setRole(usuarioAtualizado.getRole());
            return usuarioRepository.save(usuario);
        }) .orElseThrow(()-> new RuntimeException("Usuário Não encontrado"));
    }


    // deleta o usuario pelo id
    public void deletarUsuario(Long id){
        usuarioRepository.deleteById(id);
    }
}
