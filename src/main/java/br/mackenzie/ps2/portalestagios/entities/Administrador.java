package br.mackenzie.ps2.portalestagios.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@Table(name = "administradores")
@ToString
public class Administrador {
    @Id @GeneratedValue
    private Long id;
    private String nome;
    private String email;

}
