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

import br.mackenzie.ps2.portalestagios.entities.Empresa;
import br.mackenzie.ps2.portalestagios.repository.empresaRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin(origins = "*")
@Tag(name = "Empresas", description = "Endpoints para o gerenciamento de empresas parceiras")
public class empresaController {

    @Autowired
    private empresaRepository empresaRep;

    @Operation(summary = "Cria uma nova empresa", description = "Cria o cadastro de uma nova empresa. Todos os campos são obrigatórios.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Empresa criada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Requisição inválida. Verifique os campos obrigatórios.")
    })
    @PostMapping("/empresa")
    public Empresa createEmpresa(@RequestBody Empresa newEmpresa){
        if(newEmpresa.getNome() == null || newEmpresa.getCnpj() == null ||
                newEmpresa.getEmail() == null || newEmpresa.getSenha() == null ||
                newEmpresa.getNome().isEmpty() || newEmpresa.getCnpj().isEmpty() ||
                newEmpresa.getEmail().isEmpty() || newEmpresa.getSenha().isEmpty()){

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Todos os campos são obrigatórios");
        }
        return empresaRep.save(newEmpresa);
    }

    @Operation(summary = "Lista todas as empresas", description = "Retorna uma lista com todas as empresas cadastradas.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de empresas retornada com sucesso")
    })
    @GetMapping("/empresa")
    public List<Empresa> getAllEmpresas(){
        return empresaRep.findAll();
    }

    @Operation(summary = "Atualiza uma empresa", description = "Atualiza os dados de uma empresa existente com base no ID. A senha só é atualizada se for fornecida.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Empresa atualizada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Empresa não encontrada para o ID fornecido", content = @Content)
    })
    @PutMapping("/empresa/{id}")
    public Empresa updateEmpresaById(@RequestBody Empresa newData, @PathVariable Long id){
        Optional<Empresa> optional = empresaRep.findById(id);
        if(optional.isPresent()){
            Empresa newEmpresa = optional.get();
            newEmpresa.setNome(newData.getNome());
            newEmpresa.setCnpj(newData.getCnpj());
            newEmpresa.setEmail(newData.getEmail());
            if(newData.getSenha() != null && !newData.getSenha().isEmpty()) {
                newEmpresa.setSenha(newData.getSenha());
            }
            return empresaRep.save(newEmpresa);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Empresa não encontrada");
    }

    @Operation(summary = "Exclui uma empresa", description = "Exclui permanentemente o cadastro de uma empresa com base no ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Empresa excluída com sucesso"),
        @ApiResponse(responseCode = "404", description = "Empresa não encontrada para o ID fornecido", content = @Content)
    })
    @DeleteMapping("/empresa/{id}")
    public Empresa deleteEmpresaById(@PathVariable Long id){
        Optional<Empresa> optional = empresaRep.findById(id);
        if(optional.isPresent()){
            Empresa del = optional.get();
            empresaRep.deleteById(id);
            return del;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Empresa não encontrada");
    }
}
