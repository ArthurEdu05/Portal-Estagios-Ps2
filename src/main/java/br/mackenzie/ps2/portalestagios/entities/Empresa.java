package br.mackenzie.ps2.portalestagios.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "empresas")
@ToString
@Schema(description = "Representa uma Empresa parceira que oferece vagas de estágio")
public class Empresa {

    @Id @GeneratedValue
    @Schema(description = "Identificador único da empresa", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Nome da empresa", example = "Tech Solutions S.A.")
    private String nome;

    @Schema(description = "CNPJ da empresa", example = "12.345.678/0001-90")
    private String cnpj;

    @Schema(description = "E-mail de contato da empresa", example = "contato@techsolutions.com")
    private String email;

    @Schema(description = "Senha de acesso para o portal da empresa", example = "456789", accessMode = Schema.AccessMode.WRITE_ONLY)
    private String senha; 
}