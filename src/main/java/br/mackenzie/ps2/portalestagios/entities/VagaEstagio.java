package br.mackenzie.ps2.portalestagios.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "vagas_estagios")
@ToString

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
    private long id;
    private String titulo;
    private String descricao;

    @Column(name = "data_inicio")
    private Date dataInicio;

    @Column(name = "data_fim")
    private Date dataFim;

    @ManyToMany
    private List<AreaInteresse> listAreaInteresse;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    @Column(nullable = false)
    private String localizacao;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ModalidadeVaga modalidade;

    @Column(name = "carga_horaria", nullable = false)
    private String cargaHoraria;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String requisitos;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusVaga status;
}