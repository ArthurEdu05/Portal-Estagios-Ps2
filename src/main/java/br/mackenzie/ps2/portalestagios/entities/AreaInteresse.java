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
@Table(name = "areas_interesses")
@ToString
@Schema(description = "Representa uma área de interesse que pode ser associada a vagas de estágio")
public class AreaInteresse {
    @Id @GeneratedValue
    @Schema(description = "Identificador único da área de interesse", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Título da área de interesse", example = "Desenvolvimento Back-end")
    private String titulo;

    @Schema(description = "Descrição sobre a área de interesse", example = "Envolve a criação da lógica de servidor, bancos de dados e APIs.")
    private String descricao;

}
