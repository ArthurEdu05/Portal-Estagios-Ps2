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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin(origins = "*")
@Tag(name = "Administradores", description = "Endpoints para administradores do sistema")
public class administradorController {

    @Autowired
    private administradorRepository admRep;

    @Operation(summary = "Cria um novo administrador", description = "Cria um novo administrador com nome, email e senha.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Administrador criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Requisição inválida. Nome, email e senha são obrigatórios.")
    })
    @PostMapping("/admin")
    public Administrador createAdministrador(@RequestBody Administrador newAdministrador){
        if(newAdministrador.getNome() == null || newAdministrador.getEmail() == null || newAdministrador.getSenha() == null ||
        newAdministrador.getNome().isEmpty() || newAdministrador.getEmail().isEmpty() || newAdministrador.getSenha().isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nome, email e senha são obrigatórios");
        }
        return admRep.save(newAdministrador);
    }

    @Operation(summary = "Lista todos os administradores", description = "Retorna uma lista com todos os administradores cadastrados.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de administradores retornada com sucesso")
    })
    @GetMapping("/admin")
    public List<Administrador> getAllAdministradores(){
        return admRep.findAll();
    }

    @Operation(summary = "Atualiza um administrador", description = "Atualiza o nome e o email de um administrador existente com base no ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Administrador atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Administrador não encontrado para o ID fornecido", content = @Content)
    })
    @PutMapping("/admin/{id}")
    public Administrador updateAdministradorById(@RequestBody Administrador newData, @PathVariable Long id){
        Optional<Administrador> optional = admRep.findById(id);
        if(optional.isPresent()){
            Administrador newAdm = optional.get();
            newAdm.setNome(newData.getNome());
            newAdm.setEmail(newData.getEmail());
            return admRep.save(newAdm);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Administrador não encontrado");

    }

    @Operation(summary = "Exclui um administrador", description = "Exclui permanentemente um administrador com base no ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Administrador excluído com sucesso"),
        @ApiResponse(responseCode = "404", description = "Administrador não encontrado para o ID fornecido", content = @Content)
    })
    @DeleteMapping("/admin/{id}")
    public Administrador deleteAdministradorById(@PathVariable Long id){
        Optional<Administrador> optional = admRep.findById(id);
        if(optional.isPresent()){
            Administrador del = optional.get();
            admRep.deleteById(id);
            return del;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Administrador não encontrado");
    }
}
