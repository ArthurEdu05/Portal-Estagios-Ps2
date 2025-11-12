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

import br.mackenzie.ps2.portalestagios.entities.Inscricao;
import br.mackenzie.ps2.portalestagios.repository.inscricaoRepository;

@RestController
@CrossOrigin(origins = "*")
public class inscricaoController {
    @Autowired
    private inscricaoRepository inscricaoRep;
    List<Inscricao> lstInscricoes = new ArrayList<>();

    //CRUD - INSCRICAO

    //CREATE
    @PostMapping("/inscricao")
    public Inscricao createInscricao(@RequestBody Inscricao newInscricao){
        if(newInscricao.getDataInscricao() == null || newInscricao.getStatus() == null || newInscricao.getVagaEstagio() == null || newInscricao.getEstudante() == null
        || newInscricao.getStatus().isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return inscricaoRep.save(newInscricao);
    }
    //READ
    @GetMapping("/inscricao")
    public List<Inscricao> getAllInscricoes(){
        return inscricaoRep.findAll();
    }
    //UPDATE
    @PutMapping("/inscricao/{id}")
    public Inscricao updateInscricaoById(@RequestBody Inscricao newData, @PathVariable Long id){
        Optional<Inscricao> optional = inscricaoRep.findById(id);
        if(optional.isPresent()){
            Inscricao newInscricao = optional.get();
            newInscricao.setDataInscricao(newData.getDataInscricao());
            newInscricao.setStatus(newData.getStatus());
            newInscricao.setVagaEstagio(newData.getVagaEstagio());
            newInscricao.setEstudante(newData.getEstudante());
            return inscricaoRep.save(newInscricao);
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    //DELETE
    @DeleteMapping("/inscricao/{id}")
    public Inscricao deleteInscricaoById(@PathVariable Long id){
        Optional<Inscricao> optional = inscricaoRep.findById(id);
        if(optional.isPresent()){
            Inscricao del = optional.get();
            inscricaoRep.deleteById(id);
            return del;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    
}
