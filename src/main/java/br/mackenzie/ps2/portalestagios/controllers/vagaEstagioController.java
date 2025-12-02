package br.mackenzie.ps2.portalestagios.controllers;

import java.util.Date;
import java.util.Calendar;
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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@CrossOrigin(origins = "*")
@Tag(name = "Vagas de Estágio", description = "Endpoints para gerenciar as vagas de estágio")
public class vagaEstagioController {
    @Autowired
    private vagaEstagioRepository vagaEstagioRep;

    @Operation(summary = "Cria uma nova vaga de estágio", description = "Cria uma nova vaga com os dados fornecidos. O status da vaga (ABERTA/FECHADA) é definido com base na data de início.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Vaga criada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Requisição inválida. Verifique os campos obrigatórios.", content = @Content)
    })
    @PostMapping("/vagaEstagio")
    public VagaEstagio createVagaEstagio(@RequestBody VagaEstagio newVagaEstagio) {
        if (newVagaEstagio.getTitulo() == null || newVagaEstagio.getDescricao() == null ||
                newVagaEstagio.getDataInicio() == null || newVagaEstagio.getDataFim() == null ||
                newVagaEstagio.getListAreaInteresse() == null || newVagaEstagio.getTitulo().isEmpty() ||
                newVagaEstagio.getDescricao().isEmpty() || newVagaEstagio.getListAreaInteresse().isEmpty() ||
                newVagaEstagio.getLocalizacao() == null || newVagaEstagio.getLocalizacao().isEmpty() ||
                newVagaEstagio.getModalidade() == null || newVagaEstagio.getCargaHoraria() == null ||
                newVagaEstagio.getCargaHoraria().isEmpty() || newVagaEstagio.getRequisitos() == null ||
                newVagaEstagio.getRequisitos().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Todos os campos obrigatórios devem ser preenchidos");
        }
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        today.set(Calendar.MILLISECOND, 0);
        Date currentDate = today.getTime();

        Calendar vagaInicioCal = Calendar.getInstance();
        vagaInicioCal.setTime(newVagaEstagio.getDataInicio());
        vagaInicioCal.set(Calendar.HOUR_OF_DAY, 0);
        vagaInicioCal.set(Calendar.MINUTE, 0);
        vagaInicioCal.set(Calendar.SECOND, 0);
        vagaInicioCal.set(Calendar.MILLISECOND, 0);
        Date vagaInicioDate = vagaInicioCal.getTime();

        if (vagaInicioDate.after(currentDate)) {
            newVagaEstagio.setStatus(VagaEstagio.StatusVaga.FECHADA); 
        } else {
            newVagaEstagio.setStatus(VagaEstagio.StatusVaga.ABERTA); 
        }
        return vagaEstagioRep.save(newVagaEstagio);
    }

    @Operation(summary = "Lista todas as vagas de estágio", description = "Retorna uma lista com todas as vagas de estágio cadastradas no sistema.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de vagas retornada com sucesso")
    })
    @GetMapping("/vagaEstagio")
    public List<VagaEstagio> getAllVagasEstagios() {
        return vagaEstagioRep.findAll();
    }

    @Operation(summary = "Atualiza uma vaga de estágio", description = "Atualiza os dados de uma vaga de estágio existente com base no ID fornecido.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Vaga atualizada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Vaga não encontrada para o ID fornecido", content = @Content)
    })
    @PutMapping("/vagaEstagio/{id}")
    public VagaEstagio updateVagaEstagioById(@RequestBody VagaEstagio newData, @PathVariable Long id) {
        Optional<VagaEstagio> optional = vagaEstagioRep.findById(id);
        if (optional.isPresent()) {
            VagaEstagio vagaExistente = optional.get();
            vagaExistente.setTitulo(newData.getTitulo());
            vagaExistente.setDescricao(newData.getDescricao());
            vagaExistente.setDataInicio(newData.getDataInicio());
            vagaExistente.setDataFim(newData.getDataFim());
            vagaExistente.setListAreaInteresse(newData.getListAreaInteresse());
            vagaExistente.setLocalizacao(newData.getLocalizacao());
            vagaExistente.setModalidade(newData.getModalidade());
            vagaExistente.setCargaHoraria(newData.getCargaHoraria());
            vagaExistente.setRequisitos(newData.getRequisitos());
            return vagaEstagioRep.save(vagaExistente);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Encerra uma vaga", description = "Altera o status de uma vaga para 'FECHADA' com base no ID fornecido.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Vaga encerrada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Vaga não encontrada para o ID fornecido", content = @Content)
    })
    @PutMapping("/vagaEstagio/{id}/encerrar")
    public VagaEstagio encerrarVaga(@PathVariable Long id) {
        Optional<VagaEstagio> optional = vagaEstagioRep.findById(id);
        if (optional.isPresent()) {
            VagaEstagio vaga = optional.get();
            vaga.setStatus(VagaEstagio.StatusVaga.FECHADA);
            return vagaEstagioRep.save(vaga);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vaga não encontrada");
    }

    @Operation(summary = "Reabre uma vaga", description = "Altera o status de uma vaga para 'ABERTA' com base no ID fornecido.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Vaga reaberta com sucesso"),
        @ApiResponse(responseCode = "404", description = "Vaga não encontrada para o ID fornecido", content = @Content)
    })
    @PutMapping("/vagaEstagio/{id}/reabrir")
    public VagaEstagio reabrirVaga(@PathVariable Long id) {
        Optional<VagaEstagio> optional = vagaEstagioRep.findById(id);
        if (optional.isPresent()) {
            VagaEstagio vaga = optional.get();
            vaga.setStatus(VagaEstagio.StatusVaga.ABERTA);
            return vagaEstagioRep.save(vaga);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vaga não encontrada");
    }

    @Operation(summary = "Exclui uma vaga de estágio", description = "Exclui permanentemente uma vaga de estágio com base no ID fornecido.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Vaga excluída com sucesso"),
        @ApiResponse(responseCode = "404", description = "Vaga não encontrada para o ID fornecido", content = @Content)
    })
    @DeleteMapping("/vagaEstagio/{id}")
    public void deleteVagaEstagioById(@PathVariable Long id) {
        if (vagaEstagioRep.existsById(id)) {
            vagaEstagioRep.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}