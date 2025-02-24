package com.tary.ServiceFlow.services;

import com.tary.ServiceFlow.entities.Tecnico;
import com.tary.ServiceFlow.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    TecnicoRepository tecnicoRepository;

    public Tecnico salvarTecnico(Tecnico tecnico){
        return tecnicoRepository.save(tecnico);
    }

    public List<Tecnico> listarTecnicos(){
        return tecnicoRepository.findAll();
    }

    public Optional<Tecnico> buscarPorId(Long id){
        return tecnicoRepository.findById(id);
    }

    public Tecnico atualizarTecnico(Long id, Tecnico tecnicoAtualizado){
        return tecnicoRepository.findById(id).map(tecnico -> {
            tecnico.setNome(tecnicoAtualizado.getNome());
            tecnico.setCpf(tecnicoAtualizado.getCpf());
            tecnico.setEmail(tecnicoAtualizado.getEmail());
            tecnico.setTelefone(tecnicoAtualizado.getTelefone());
            tecnico.setEspecialidade(tecnicoAtualizado.getEspecialidade());
            return tecnicoRepository.save(tecnico);
        }).orElseThrow(() -> new RuntimeException("Técnico não encontrado"));
    }
}
