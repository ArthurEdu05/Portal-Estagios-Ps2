package br.mackenzie.ps2.portalestagios.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class Administrador {
    @Id @GeneratedValue
    private Long id;
    private String nome;
    private String email;

}
