package com.tary.ServiceFlow.controllers;

import com.tary.ServiceFlow.entities.Tecnico;
import com.tary.ServiceFlow.services.TecnicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tecnicos")
public class TecnicoController {

    @Autowired
    private TecnicoService tecnicoService;

    @PostMapping
    public Tecnico criarTecnico(@RequestBody Tecnico tecnico){
        return tecnicoService.salvarTecnico(tecnico);
    }

    @GetMapping
    public List<Tecnico> listarTecnicos(){
        return tecnicoService.listarTecnicos();
    }
}
