package br.mackenzie.ps2.portalestagios.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    private String email;
    private String senha;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class LoginResponse {
    private boolean sucesso;
    private String mensagem;
    private String tipo;
    private Object dados;
}