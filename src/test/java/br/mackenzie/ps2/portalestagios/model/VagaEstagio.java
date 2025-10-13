package br.mackenzie.ps2.portalestagios.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@ToString

public class VagaEstagio {

@Id @GeneratedValue
    private long id;
    private String titulo;
    private String descricao;
    private Date dataInicio;
    private Date dataFim;
    private List<AreaInteresse> listAreaInteresse;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;
}
