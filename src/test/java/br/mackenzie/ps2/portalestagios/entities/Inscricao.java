package br.mackenzie.ps2.portalestagios.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.ToString;
import java.util.Date;
import java.util.List;

@Data
@Entity
@ToString

public class Inscricao {

@Id @GeneratedValue
private long id;
private Date dataInscricao;
private String status;
private VagaEstagio vagaEstagio;
private List<Estudante> estudantes;
}
