package br.mackenzie.ps2.portalestagios.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@ToString

public class Usuario {
    @Id @GeneratedValue
    private long id;
    private String login;
    private String senha;
}
