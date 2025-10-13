package br.mackenzie.ps2.portalestagios.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import java.util.List;


@Getter
@Setter
@Entity

public class Inscricao {

@Id @GeneratedValue
private long id;
private Date dataInscricao;
private int status;
private VagaEstagio vagaEstagio;
private List<Estudante> estudantes;
}
