package br.mackenzie.ps2.portalestagios.entities;


import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Representa a inscrição de um Estudante em uma Vaga de Estágio")
public class Inscricao {

    @Id @GeneratedValue
    @Schema(description = "Identificador único da inscrição", accessMode = Schema.AccessMode.READ_ONLY)
    private long id;

    @Column(name = "data_inscricao")
    @Schema(description = "Data em que a inscrição foi realizada.", example = "2024-05-10 10:00:00+00", format = "date-time")
    private Date dataInscricao;

    @Schema(description = "Status atual da inscrição", example = "PENDENTE")
    private String status;


    @ManyToOne 
    @JoinColumn(name = "vaga_id") 
    @Schema(description = "Vaga de estágio à qual o estudante se inscreveu")
    private VagaEstagio vagaEstagio;

    @ManyToOne 
    @JoinColumn(name = "estudante_id") 
    @Schema(description = "Estudante que realizou a inscrição")
    private Estudante estudante; 
}
