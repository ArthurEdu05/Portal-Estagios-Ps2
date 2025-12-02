package br.mackenzie.ps2.portalestagios.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Entity
@Table(name = "vagas_estagios")
@ToString
@Schema(description = "Representa uma Vaga de Estágio oferecida por uma Empresa")
public class VagaEstagio {

    public enum ModalidadeVaga {
        PRESENCIAL,
        REMOTO,
        HIBRIDO
    }

    public enum StatusVaga {
        ABERTA,
        FECHADA
    }

    @Id
    @GeneratedValue
    @Schema(description = "Identificador único da vaga", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private long id;

    @Schema(description = "Título da vaga de estágio", example = "Desenvolvedor Java Jr.")
    private String titulo;

    @Schema(description = "Descrição detalhada da vaga e das atividades", example = "Atuar no desenvolvimento e manutenção de sistemas legados em Java.")
    private String descricao;

    @Column(name = "data_inicio")
    @Schema(description = "Data de início do estágio.", example = "2024-08-01 00:00:00+00", format = "date-time")
    private Date dataInicio;

    @Column(name = "data_fim")
    @Schema(description = "Data de término do estágio.", example = "2025-08-01 00:00:00+00", format = "date-time")
    private Date dataFim;

    @ManyToMany
    @Schema(description = "Lista de áreas de interesse relacionadas à vaga")
    private List<AreaInteresse> listAreaInteresse;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    @Schema(description = "Empresa que está oferecendo a vaga")
    private Empresa empresa;

    @Column(nullable = false)
    @Schema(description = "Localização do estágio (estado ou 'Remoto')", example = "SP")
    private String localizacao;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @Schema(description = "Modalidade da vaga (PRESENCIAL, REMOTO ou HIBRIDO", example = "HIBRIDO")
    private ModalidadeVaga modalidade;

    @Column(name = "carga_horaria", nullable = false)
    @Schema(description = "Carga horária diária", example = "8 horas")
    private String cargaHoraria;

    @Column(nullable = false, columnDefinition = "TEXT")
    @Schema(description = "Requisitos e qualificações para a vaga", example = "Cursando Ciência da Computação, conhecimento em Spring Boot e SQL.")
    private String requisitos;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @Schema(description = "Status atual da vaga (ABERTA ou FECHADA)", example = "ABERTA", accessMode = Schema.AccessMode.READ_ONLY)
    private StatusVaga status;
}