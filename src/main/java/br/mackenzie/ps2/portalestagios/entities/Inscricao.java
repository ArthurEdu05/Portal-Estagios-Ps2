package br.mackenzie.ps2.portalestagios.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;
import java.util.Date;

@Data
@Entity
@Table(name = "inscricoes")
@ToString

public class Inscricao {

@Id @GeneratedValue
private long id;

@Column(name = "data_inscricao")
private Date dataInscricao;
private String status;


    @ManyToOne 
    @JoinColumn(name = "vaga_id") 
    private VagaEstagio vagaEstagio;

    @ManyToOne 
    @JoinColumn(name = "estudante_id") 
    private Estudante estudante; 
}
