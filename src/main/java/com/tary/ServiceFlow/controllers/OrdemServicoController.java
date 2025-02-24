package com.tary.ServiceFlow.controllers;


import com.tary.ServiceFlow.entities.OrdemServico;
import com.tary.ServiceFlow.services.OrdemServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ordens")
public class OrdemServicoController {

    @Autowired
    private OrdemServicoService ordemServicoService;

    @PostMapping
    public OrdemServico criarOrdem(@RequestBody OrdemServico ordem){
        return ordemServicoService.criarOrdemServico(ordem);

    }

    @GetMapping
    public List<OrdemServico> listarOrdens(){
        return ordemServicoService.listarOrdensServico();
    }
}
