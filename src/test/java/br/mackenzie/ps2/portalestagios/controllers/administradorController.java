package br.mackenzie.ps2.portalestagios.controllers;

import br.mackenzie.ps2.portalestagios.entities.Administrador;
import br.mackenzie.ps2.portalestagios.repository.administradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class administradorController {

    @Autowired
    private administradorRepository rep;


    //CREATE
    @PostMapping("/administradores")
    public Administrador criarAdministrador(@ResquestBody ador novoAdministrador){}

}
