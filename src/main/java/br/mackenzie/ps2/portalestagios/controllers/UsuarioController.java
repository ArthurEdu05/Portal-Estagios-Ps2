package br.mackenzie.ps2.portalestagios.controllers;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class UsuarioController {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private String tipoUsuario; //AQUI NOS FALAMOS O TIPO DE USUARIO

    // AQUI DEFINIMOS PERMISSÕES DE CADA!
    public abstract boolean podeAcessarVagas();
    public abstract boolean podeCriarVagas();
    public abstract boolean podeGerenciarUsuarios();
}