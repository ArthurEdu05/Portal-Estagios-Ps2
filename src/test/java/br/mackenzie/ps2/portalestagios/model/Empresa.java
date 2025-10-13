package br.mackenzie.ps2.portalestagios.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@Entity
@ToString

public class Empresa {

    @Id @GeneratedValue
    private Long id;
    private String nome;
    private String cnpj;
    private String email;

}
