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

public class Usuario {
    @Id @GeneratedValue
    private long id;
    private String login;
    private String senha;
}
