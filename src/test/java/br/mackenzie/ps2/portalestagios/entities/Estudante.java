package br.mackenzie.ps2.portalestagios.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@Entity
@ToString

public class Estudante {
    @Id @GeneratedValue
    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private List<AreaInteresse> listAreaInteresse;
}
