package br.mackenzie.ps2.portalestagios.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@Entity
@Table(name = "estudantes")
@ToString

public class Estudante {
    @Id @GeneratedValue
    private Long id;
    private String nome;
    private String cpf;
    private String email;
    @ManyToMany
    private List<AreaInteresse> listAreaInteresse;
}
