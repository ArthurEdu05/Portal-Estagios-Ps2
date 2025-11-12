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

import br.mackenzie.ps2.portalestagios.entities.AreaInteresse;
import br.mackenzie.ps2.portalestagios.repository.areaInteresseRepository;

@RestController
@CrossOrigin(origins = "*")
public class areaInteresseController {

    @Autowired
    private areaInteresseRepository areaRep;
    List<AreaInteresse> lstAreas = new ArrayList<>();

    //CRUD - AREA DE INTERESSE

    //CREATE
    @PostMapping("/areaInteresse")
    public AreaInteresse createAreaInteresse(@RequestBody AreaInteresse newAreaInteresse){
        if(newAreaInteresse.getTitulo() == null || newAreaInteresse.getDescricao() == null
        || newAreaInteresse.getTitulo().isEmpty() || newAreaInteresse.getDescricao().isEmpty()){
            
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        }
        return areaRep.save(newAreaInteresse);
    }

    //READ
    @GetMapping("/areaInteresse")
    public List<AreaInteresse> getAllAreaInteresse(){
        return areaRep.findAll();
    }

    //UPDATE
    @PutMapping("/areaInteresse/{id}")
    public AreaInteresse updateAreaInteresseById(@RequestBody AreaInteresse newData, @PathVariable Long id){
        Optional<AreaInteresse> optional = areaRep.findById(id);
        if(optional.isPresent()){
            AreaInteresse newArea = optional.get();
            newArea.setTitulo(newData.getTitulo());
            newArea.setDescricao(newData.getDescricao());
            return areaRep.save(newArea);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);

    }
    //DELETE
    @DeleteMapping("/areaInteresse/{id}")
    public AreaInteresse deleteAreaInteresseById(@PathVariable Long id){
        Optional<AreaInteresse> optional = areaRep.findById(id);
        if(optional.isPresent()){
            AreaInteresse del = optional.get();
            areaRep.deleteById(id);
            return del;
        }
        
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);

    }
}
