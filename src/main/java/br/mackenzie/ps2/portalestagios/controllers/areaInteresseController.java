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

import br.mackenzie.ps2.portalestagios.entities.AreaInteresse;
import br.mackenzie.ps2.portalestagios.repository.areaInteresseRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin(origins = "*")
@Tag(name = "Áreas de Interesse", description = "Endpoints para o gerenciamento de áreas de interesse")
public class areaInteresseController {

    @Autowired
    private areaInteresseRepository areaRep;

    @Operation(summary = "Cria uma nova área de interesse", description = "Cria uma nova área de interesse com título e descrição.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Área de interesse criada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Requisição inválida. Título e descrição são obrigatórios.")
    })
    @PostMapping("/areaInteresse")
    public AreaInteresse createAreaInteresse(@RequestBody AreaInteresse newAreaInteresse){
        if(newAreaInteresse.getTitulo() == null || newAreaInteresse.getDescricao() == null
        || newAreaInteresse.getTitulo().isEmpty() || newAreaInteresse.getDescricao().isEmpty()){
            
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Título e descrição são obrigatórios");

        }
        return areaRep.save(newAreaInteresse);
    }

    @Operation(summary = "Lista todas as áreas de interesse", description = "Retorna uma lista com todas as áreas de interesse cadastradas.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de áreas retornada com sucesso")
    })
    @GetMapping("/areaInteresse")
    public List<AreaInteresse> getAllAreaInteresse(){
        return areaRep.findAll();
    }

    @Operation(summary = "Atualiza uma área de interesse", description = "Atualiza o título e a descrição de uma área de interesse existente com base no ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Área de interesse atualizada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Área de interesse não encontrada para o ID fornecido", content = @Content)
    })
    @PutMapping("/areaInteresse/{id}")
    public AreaInteresse updateAreaInteresseById(@RequestBody AreaInteresse newData, @PathVariable Long id){
        Optional<AreaInteresse> optional = areaRep.findById(id);
        if(optional.isPresent()){
            AreaInteresse newArea = optional.get();
            newArea.setTitulo(newData.getTitulo());
            newArea.setDescricao(newData.getDescricao());
            return areaRep.save(newArea);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Área de interesse não encontrada");

    }
    
    @Operation(summary = "Exclui uma área de interesse", description = "Exclui permanentemente uma área de interesse com base no ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Área de interesse excluída com sucesso"),
        @ApiResponse(responseCode = "404", description = "Área de interesse não encontrada para o ID fornecido", content = @Content)
    })
    @DeleteMapping("/areaInteresse/{id}")
    public AreaInteresse deleteAreaInteresseById(@PathVariable Long id){
        Optional<AreaInteresse> optional = areaRep.findById(id);
        if(optional.isPresent()){
            AreaInteresse del = optional.get();
            areaRep.deleteById(id);
            return del;
        }
        
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Área de interesse não encontrada");

    }
}
