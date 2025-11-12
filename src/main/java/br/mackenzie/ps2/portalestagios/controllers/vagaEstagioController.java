package br.mackenzie.ps2.portalestagios.controllers;

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

import br.mackenzie.ps2.portalestagios.entities.VagaEstagio;
import br.mackenzie.ps2.portalestagios.repository.vagaEstagioRepository;

@RestController
@CrossOrigin(origins = "*")
public class vagaEstagioController {
    @Autowired
    private vagaEstagioRepository vagaEstagioRep;

    //CRUD - VAGA DE ESTAGIO

    //CREATE
    @PostMapping("/vagaEstagio")
    public VagaEstagio createVagaEstagio(@RequestBody VagaEstagio newVagaEstagio){
        if(newVagaEstagio.getTitulo() == null || newVagaEstagio.getDescricao() == null || newVagaEstagio.getDataInicio() == null || newVagaEstagio.getDataFim() == null || newVagaEstagio.getListAreaInteresse() == null
        || newVagaEstagio.getTitulo().isEmpty() || newVagaEstagio.getDescricao().isEmpty() || newVagaEstagio.getListAreaInteresse().isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return vagaEstagioRep.save(newVagaEstagio);
    }
    //READ
    @GetMapping("/vagaEstagio")
    public List<VagaEstagio> getAllVagasEstagios(){
        return vagaEstagioRep.findAll();
    }
    //UPDATE
    @PutMapping("/vagaEstagio/{id}")
    public VagaEstagio updateVagaEstagioById(@RequestBody VagaEstagio newData, @PathVariable Long id){
        Optional<VagaEstagio> optional = vagaEstagioRep.findById(id);
        if(optional.isPresent()){
            VagaEstagio newVagaEstagio = optional.get();
            newVagaEstagio.setTitulo(newData.getTitulo());
            newVagaEstagio.setDescricao(newData.getDescricao());
            newVagaEstagio.setDataInicio(newData.getDataInicio());
            newVagaEstagio.setDataFim(newData.getDataFim());
            newVagaEstagio.setListAreaInteresse(newData.getListAreaInteresse());
            return vagaEstagioRep.save(newVagaEstagio);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    //DELETE
    @DeleteMapping("/vagaEstagio/{id}")
    public VagaEstagio deleteVagaEstagioById(@PathVariable Long id){
        Optional<VagaEstagio> optional = vagaEstagioRep.findById(id);
        if(optional.isPresent()){
            VagaEstagio del = optional.get();
            vagaEstagioRep.deleteById(id);
            return del;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
