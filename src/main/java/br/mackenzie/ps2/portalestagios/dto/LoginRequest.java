package br.mackenzie.ps2.portalestagios.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * DTO (Data Transfer Object) para encapsular os dados de requisição de login de um usuário
 *
 * Esta classe é utilizada para receber as credenciais (e-mail e senha) enviadas pelo cliente
 * durante o processo de autenticação. Ela desacopla a estrutura de entrada da API
 * da entidade interna de {@code Usuario}, garantindo que apenas os dados necessários
 * para o login sejam transferidos e processados.
 *
 * Os campos {@code email} e {@code senha} são as credenciais esperadas.
 */

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


/**
 * DTO para encapsular a resposta da operação de login (não está sendo utilizado ativamente como tipo de retorno ou objeto criado explicitamente nos controllers ou services atualmente. 
 * É um DTO planejado para futuras implementações de resposta da API
 *
 * Esta classe é utilizada para padronizar a saída da API após uma tentativa de login,
 * informando ao cliente o resultado da operação de forma clara e estruturada.
 * Isso inclui se o login foi bem-sucedido, uma mensagem descritiva, o tipo de usuário
 * autenticado e os dados relevantes do usuário, se aplicável
 *
 * Os campos {@code sucesso}, {@code mensagem}, {@code tipo} e {@code dados}
 * fornecem um feedback completo sobre a autenticação
 */
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