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

import br.mackenzie.ps2.portalestagios.entities.Inscricao;
import br.mackenzie.ps2.portalestagios.repository.inscricaoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin(origins = "*")
@Tag(name = "Inscrições", description = "Endpoints para o gerenciamento de inscrições de estudantes em vagas")
public class inscricaoController {
    @Autowired
    private inscricaoRepository inscricaoRep;

    @Operation(summary = "Cria uma nova inscrição", description = "Inscreve um estudante em uma vaga de estágio.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Inscrição realizada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Requisição inválida. Verifique os campos obrigatórios.")
    })
    @PostMapping("/inscricao")
    public Inscricao createInscricao(@RequestBody Inscricao newInscricao){
        if(newInscricao.getDataInscricao() == null || newInscricao.getStatus() == null || newInscricao.getVagaEstagio() == null || newInscricao.getEstudante() == null
        || newInscricao.getStatus().isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Todos os campos são obrigatórios");
        }
        return inscricaoRep.save(newInscricao);
    }

    @Operation(summary = "Lista todas as inscrições", description = "Retorna uma lista com todas as inscrições realizadas.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de inscrições retornada com sucesso")
    })
    @GetMapping("/inscricao")
    public List<Inscricao> getAllInscricoes(){
        return inscricaoRep.findAll();
    }

    @Operation(summary = "Atualiza uma inscrição", description = "Atualiza os dados de uma inscrição existente com base no ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Inscrição atualizada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Inscrição não encontrada para o ID fornecido", content = @Content)
    })
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

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Inscrição não encontrada");
    }

    @Operation(summary = "Exclui uma inscrição", description = "Exclui permanentemente uma inscrição com base no ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Inscrição excluída com sucesso"),
        @ApiResponse(responseCode = "404", description = "Inscrição não encontrada para o ID fornecido", content = @Content)
    })
    @DeleteMapping("/inscricao/{id}")
    public Inscricao deleteInscricaoById(@PathVariable Long id){
        Optional<Inscricao> optional = inscricaoRep.findById(id);
        if(optional.isPresent()){
            Inscricao del = optional.get();
            inscricaoRep.deleteById(id);
            return del;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Inscrição não encontrada");
    }
    
}
