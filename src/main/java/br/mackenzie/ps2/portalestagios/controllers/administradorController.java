package br.mackenzie.ps2.portalestagios.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.mackenzie.ps2.portalestagios.entities.Administrador;
import br.mackenzie.ps2.portalestagios.repository.administradorRepository;

@RestController
@CrossOrigin(origins = "*")
public class administradorController {

    @Autowired
    private administradorRepository admRep;
    List<Administrador> lstAdm = new ArrayList<>();

    //CRUD - ADM

    //CREATE
    @PostMapping("/admin")
    public Administrador createAdministrador(@RequestBody Administrador newAdministrador){
        if(newAdministrador.getNome() == null || newAdministrador.getEmail() == null ||
        newAdministrador.getNome().isEmpty() || newAdministrador.getEmail().isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return admRep.save(newAdministrador);
    }

    //READ
    @GetMapping("/admin")
    public List<Administrador> getAllAdministradores(){
        return admRep.findAll();
    }

    //UPDATE
    @PutMapping("/admin/{id}")
    public Administrador updateAdministradorById(@RequestBody Administrador newData, @PathVariable Long id){
        Optional<Administrador> optional = admRep.findById(id);
        if(optional.isPresent()){
            Administrador newAdm = optional.get();
            newAdm.setNome(newData.getNome());
            newAdm.setEmail(newData.getEmail());
            return admRep.save(newAdm);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);

    }

    //DELETE
    @DeleteMapping("/admin/{id}")
    public Administrador deleteAdministradorById(@PathVariable Long id){
        Optional<Administrador> optional = admRep.findById(id);
        if(optional.isPresent()){
            Administrador del = optional.get();
            admRep.deleteById(id);
            return del;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
