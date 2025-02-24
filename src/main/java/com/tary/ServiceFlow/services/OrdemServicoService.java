package com.tary.ServiceFlow.services;

import com.tary.ServiceFlow.entities.OrdemServico;
import com.tary.ServiceFlow.repositories.OrdemServicoRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class OrdemServicoService {

    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    public OrdemServico criarOrdemServico(OrdemServico ordemServico){
        ordemServico.setStatus(OrdemServico.StatusOrdemServico.PENDENTE);
        return ordemServicoRepository.save(ordemServico);
    }

    public List<OrdemServico> listarOrdensServico(){
        return ordemServicoRepository.findAll();
    }

    public Optional<OrdemServico> buscarPorId(Long id){
        return ordemServicoRepository.findById(id);
    }

    public OrdemServico atualizarOrdemServico(Long id, OrdemServico ordemAtualizada){
        return ordemServicoRepository.findById(id).map(ordem -> {
            ordem.setDescricao(ordemAtualizada.getDescricao());
            ordem.setStatus(ordemAtualizada.getStatus());
            ordem.setDataCriacao(ordemAtualizada.getDataCriacao());
            return ordemServicoRepository.save(ordem);
        }).orElseThrow(() -> new RuntimeException("Ordem de Serviço não encontrada"));
    }

    public void deletarOrdemServico(Long id){
        ordemServicoRepository.deleteById(id);
    }
}
