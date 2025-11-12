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

import br.mackenzie.ps2.portalestagios.entities.Empresa;
import br.mackenzie.ps2.portalestagios.repository.empresaRepository;

@RestController
@CrossOrigin(origins = "*")
public class empresaController {

    @Autowired
    private empresaRepository empresaRep;
    List<Empresa> lstEmpresas = new ArrayList<>();

    //CRUD - EMPRESA

    //CREATE
    @PostMapping("/empresa")
    public Empresa createEmpresa(@RequestBody Empresa newEmpresa){
        if(newEmpresa.getNome() == null || newEmpresa.getCnpj() == null || newEmpresa.getEmail() == null 
        || newEmpresa.getNome().isEmpty() || newEmpresa.getCnpj().isEmpty() || newEmpresa.getEmail().isEmpty()){
            
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        
        }

        return empresaRep.save(newEmpresa);
    }
    //READ
    @GetMapping("/empresa")
    public List<Empresa> getAllEmpresas(){
        return empresaRep.findAll();
    }

    //UPDATE
    @PutMapping("/empresa/{id}")
    public Empresa updateEmpresaById(@RequestBody Empresa newData, @PathVariable Long id){
        Optional<Empresa> optional = empresaRep.findById(id);
        if(optional.isPresent()){
            Empresa newEmpresa = optional.get();
            newEmpresa.setNome(newData.getNome());
            newEmpresa.setCnpj(newData.getCnpj());
            newEmpresa.setEmail(newData.getEmail());
            return empresaRep.save(newEmpresa);
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND);

    }

    //DELETE
    @DeleteMapping("/empresa/{id}")
    public Empresa deleteEmpresaById(@PathVariable Long id){
        Optional<Empresa> optional = empresaRep.findById(id);
        if(optional.isPresent()){
            Empresa del = optional.get();
            empresaRep.deleteById(id);
            return del;
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND);

    }
}
