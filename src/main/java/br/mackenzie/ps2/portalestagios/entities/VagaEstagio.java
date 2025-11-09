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

@Id @GeneratedValue
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
}
