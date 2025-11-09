package br.mackenzie.ps2.portalestagios.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.mackenzie.ps2.portalestagios.repository.estudanteRepository;
import br.mackenzie.ps2.portalestagios.entities.Estudante;

@RestController
public class estudanteController {
    @Autowired
    private estudanteRepository estudanteRep;
    List<Estudante> lstEstudantes = new ArrayList<>();

    //CRUD - ESTUDANTE

    //CREATE
    @PostMapping("/estudante")
    public Estudante createEstudante(@RequestBody Estudante newEstudante){
        if(newEstudante.getNome() == null || newEstudante.getCpf() == null || newEstudante.getEmail() == null || newEstudante.getListAreaInteresse() == null
        || newEstudante.getNome().isEmpty() || newEstudante.getCpf().isEmpty() || newEstudante.getEmail().isEmpty() || newEstudante.getListAreaInteresse().isEmpty()){
            
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return estudanteRep.save(newEstudante);
    }

    //READ
    @GetMapping("/estudante")
    public List<Estudante> getAllEstudantes(){
        return estudanteRep.findAll();
    }

    //UPDATE
    @PutMapping("/estudante/{id}")
    public Estudante updateEstudanteById(@RequestBody Estudante newData, @PathVariable Long id){
        Optional<Estudante> optional = estudanteRep.findById(id);
        if(optional.isPresent()){
            Estudante newEstudante = optional.get();
            newEstudante.setNome(newData.getNome());
            newEstudante.setCpf(newData.getCpf());
            newEstudante.setEmail(newData.getEmail());
            newEstudante.setListAreaInteresse(newData.getListAreaInteresse());
            return estudanteRep.save(newEstudante);
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND);

    }
    //DELETE
    @DeleteMapping("/estudante/{id}")
    public Estudante deleteEstudanteById(@PathVariable Long id){
        Optional<Estudante> optional = estudanteRep.findById(id);
        if(optional.isPresent()){
            Estudante del = optional.get();
            estudanteRep.deleteById(id);
            return del;
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND);

    }
}
