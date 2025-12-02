package br.mackenzie.ps2.portalestagios.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@Entity
@Table(name = "estudantes")
@ToString
@Schema(description = "Representa um Estudante que busca por vagas de estágio")
public class Estudante {
    @Id @GeneratedValue
    @Schema(description = "Identificador único do estudante", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Nome completo do estudante", example = "Maria Clara")
    private String nome;

    @Schema(description = "CPF do estudante", example = "123.456.789-00")
    private String cpf;

    @Schema(description = "E-mail do estudante", example = "maria.clara@email.com")
    private String email;

    @Schema(description = "Senha de acesso do estudante", example = "123456", accessMode = Schema.AccessMode.WRITE_ONLY)
    private String senha;

    @ManyToMany
    @Schema(description = "Lista de áreas de interesse do estudante")
    private List<AreaInteresse> listAreaInteresse;
}