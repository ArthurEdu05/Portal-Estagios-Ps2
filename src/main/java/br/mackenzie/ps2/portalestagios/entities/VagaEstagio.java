package br.mackenzie.ps2.portalestagios.entities;


import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Data
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
