package br.mackenzie.ps2.portalestagios.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO para requisição de login, contendo e-mail e senha do usuário.")
public class LoginRequest {
    @Schema(description = "E-mail do usuário para autenticação", required = true, example = "usuario@exemplo.com")
    private String email;
    @Schema(description = "Senha do usuário para autenticação", required = true, example = "123456")
    private String senha;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO para respostas de login, informando o resultado da operação.")
class LoginResponse {
    @Schema(description = "Indica se a operação foi bem-sucedida", example = "true")
    private boolean sucesso;

    @Schema(description = "Mensagem descritiva sobre o resultado da operação", example = "Login realizado com sucesso")
    private String mensagem;
    
    @Schema(description = "Tipo de usuário autenticado (ESTUDANTE, EMPRESA, ADMIN)", example = "ESTUDANTE")
    private String tipo;

    @Schema(description = "Dados do usuário autenticado")
    private Object dados;
}