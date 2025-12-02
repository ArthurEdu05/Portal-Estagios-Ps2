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

import br.mackenzie.ps2.portalestagios.repository.estudanteRepository;
import br.mackenzie.ps2.portalestagios.entities.Estudante;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin(origins = "*")
@Tag(name = "Estudantes", description = "Endpoints para o gerenciamento de estudantes")
public class estudanteController {
    @Autowired
    private estudanteRepository estudanteRep;

    @Operation(summary = "Cria um novo estudante", description = "Cria o cadastro de um novo estudante. Todos os campos são obrigatórios.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Estudante criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Requisição inválida. Verifique os campos obrigatórios.")
    })
    @PostMapping("/estudante")
    public Estudante createEstudante(@RequestBody Estudante newEstudante) {
        if (newEstudante.getNome() == null || newEstudante.getCpf() == null ||
                newEstudante.getEmail() == null || newEstudante.getSenha() == null ||
                newEstudante.getNome().isEmpty() || newEstudante.getCpf().isEmpty() ||
                newEstudante.getEmail().isEmpty() || newEstudante.getSenha().isEmpty()) {

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Todos os campos são obrigatórios");
        }
        return estudanteRep.save(newEstudante);
    }

    @Operation(summary = "Lista todos os estudantes", description = "Retorna uma lista com todos os estudantes cadastrados.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de estudantes retornada com sucesso")
    })
    @GetMapping("/estudante")
    public List<Estudante> getAllEstudantes() {
        return estudanteRep.findAll();
    }

    @Operation(summary = "Busca um estudante por ID", description = "Retorna os dados de um estudante específico com base no seu ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Estudante encontrado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Estudante não encontrado para o ID fornecido", content = @Content)
    })
    @GetMapping("/estudante/{id}")
    public Estudante getEstudanteById(@PathVariable Long id) {
        Optional<Estudante> optional = estudanteRep.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Estudante não encontrado");
    }

    @Operation(summary = "Atualiza um estudante", description = "Atualiza os dados de um estudante existente com base no ID. A senha só é atualizada se for fornecida.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Estudante atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Estudante não encontrado para o ID fornecido", content = @Content)
    })
    @PutMapping("/estudante/{id}")
    public Estudante updateEstudanteById(@RequestBody Estudante newData, @PathVariable Long id) {
        Optional<Estudante> optional = estudanteRep.findById(id);
        if (optional.isPresent()) {
            Estudante newEstudante = optional.get();
            newEstudante.setNome(newData.getNome());
            newEstudante.setCpf(newData.getCpf());
            newEstudante.setEmail(newData.getEmail());
            if (newData.getSenha() != null && !newData.getSenha().isEmpty()) {
                newEstudante.setSenha(newData.getSenha());
            }
            newEstudante.setListAreaInteresse(newData.getListAreaInteresse());
            return estudanteRep.save(newEstudante);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Estudante não encontrado");
    }

    @Operation(summary = "Exclui um estudante", description = "Exclui permanentemente o cadastro de um estudante com base no ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Estudante excluído com sucesso"),
        @ApiResponse(responseCode = "404", description = "Estudante não encontrado para o ID fornecido", content = @Content)
    })
    @DeleteMapping("/estudante/{id}")
    public Estudante deleteEstudanteById(@PathVariable Long id) {
        Optional<Estudante> optional = estudanteRep.findById(id);
        if (optional.isPresent()) {
            Estudante del = optional.get();
            estudanteRep.deleteById(id);
            return del;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Estudante não encontrado");
    }
}