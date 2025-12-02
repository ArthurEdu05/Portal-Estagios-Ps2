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
@Table(name = "administradores")
@ToString
@Schema(description = "Representa um Administrador do sistema")
public class Administrador {
    @Id @GeneratedValue
    @Schema(description = "Identificador único do administrador", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Nome completo do administrador", example = "João da Silva")
    private String nome;

    @Schema(description = "Endereço de e-mail do administrador", example = "joao.silva@example.com")
    private String email;

    @Schema(description = "Senha de acesso do administrador", example = "654321", accessMode = Schema.AccessMode.WRITE_ONLY)
    private String senha;

}
