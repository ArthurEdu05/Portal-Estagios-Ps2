package br.mackenzie.ps2.portalestagios.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class AreaInteresse {
    @Id @GeneratedValue
    private Long id;
    private String titulo;
    private String descricao;

}
