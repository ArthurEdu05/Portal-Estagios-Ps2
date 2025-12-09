package br.mackenzie.ps2.portalestagios.controllers;

import br.mackenzie.ps2.portalestagios.entities.Administrador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.mackenzie.ps2.portalestagios.dto.LoginRequest;
import br.mackenzie.ps2.portalestagios.entities.Estudante;
import br.mackenzie.ps2.portalestagios.entities.Empresa;
import br.mackenzie.ps2.portalestagios.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * Controlador REST responsável por gerenciar as requisições de autenticação na API.
 *
 * Esta classe expõe os endpoints para login de diferentes perfis de usuário
 * (Estudante, Empresa e Administrador). Ela atua como a interface entre as requisições HTTP
 * do cliente e a lógica de negócio de autenticação, delegando as validações de credenciais
 * para o {@link AuthService}.
 *
 * Utiliza o DTO {@link LoginRequest} para receber as credenciais e
 * retorna {@code ResponseEntity} com os dados do usuário autenticado e um token,
 * ou mensagens de erro apropriadas.
 */

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
@Tag(name = "Autenticação", description = "Endpoints para autenticação de usuários (Estudantes, Empresas e Administradores)")
public class AuthController {

    @Autowired
    private AuthService authService;

    /**
     * Endpoint unificado para login de todos os tipos de usuário (Estudante, Empresa, Administrador).
     *
     * Recebe as credenciais de login e tenta autenticar o usuário sequencialmente
     * como estudante, depois como empresa e, por fim, como administrador.
     * Retorna os dados do usuário autenticado junto com um token de acesso mockado.
     *
     * @param loginRequest Objeto {@link LoginRequest} contendo o e-mail e a senha do usuário.
     * @return {@code ResponseEntity} contendo um mapa com os dados do usuário (id, login, nome, tipo, token)
     *         e o status HTTP 200 (OK) em caso de sucesso.
     *         Retorna 400 (Bad Request) se e-mail ou senha forem inválidos ou vazios,
     *         ou 401 (Unauthorized) se as credenciais não corresponderem a nenhum usuário.
     */
    @Operation(summary = "Login unificado para todos os tipos de usuário", description = "Autentica um usuário (Estudante, Empresa ou Admin) com base no e-mail e senha e retorna os dados do usuário com um token de acesso.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Login bem-sucedido", content = @Content(mediaType = "application/json", schema = @Schema(example = "{\"id\": 1, \"login\": \"user@example.com\", \"nome\": \"Nome do Usuário\", \"tipo\": \"ESTUDANTE\", \"token\": \"mock-token-uuid\"}"))),
        @ApiResponse(responseCode = "400", description = "Email e senha são obrigatórios", content = @Content(mediaType = "application/json", schema = @Schema(example = "{\"sucesso\": false, \"mensagem\": \"Email e senha são obrigatórios\"}"))),
        @ApiResponse(responseCode = "401", description = "Credenciais inválidas", content = @Content(mediaType = "application/json", schema = @Schema(example = "{\"sucesso\": false, \"mensagem\": \"Email ou senha inválidos\"}")))
    })
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequest loginRequest) {

        if (loginRequest.getEmail() == null || loginRequest.getSenha() == null ||
                loginRequest.getEmail().isEmpty() || loginRequest.getSenha().isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(criarResposta(false, "Email e senha são obrigatórios", null, null, null));
        }

        // Tenta autenticar como estudante primeiro
        Optional<Estudante> estudante = authService.autenticarEstudante(
                loginRequest.getEmail(),
                loginRequest.getSenha());

        if (estudante.isPresent()) {
            String token = "mock-token-" + UUID.randomUUID().toString();
            Map<String, Object> userData = new HashMap<>();
            userData.put("id", estudante.get().getId());
            userData.put("login", estudante.get().getEmail());
            userData.put("nome", estudante.get().getNome());
            userData.put("tipo", "ESTUDANTE");
            userData.put("token", token);

            return ResponseEntity.ok(userData);
        }

        // Se não for estudante, tenta empresa
        Optional<Empresa> empresa = authService.autenticarEmpresa(
                loginRequest.getEmail(),
                loginRequest.getSenha());

        if (empresa.isPresent()) {
            String token = "mock-token-" + UUID.randomUUID().toString();
            Map<String, Object> userData = new HashMap<>();
            userData.put("id", empresa.get().getId());
            userData.put("login", empresa.get().getEmail());
            userData.put("nome", empresa.get().getNome());
            userData.put("tipo", "EMPRESA");
            userData.put("token", token);

            return ResponseEntity.ok(userData);
        }

        // Se não for nenhum dos anteriores, tenta admin 
        Optional<Administrador> admin = authService.autenticarAdministrador(
                loginRequest.getEmail(),
                loginRequest.getSenha());

        if (admin.isPresent()) {
            String token = "mock-token-" + UUID.randomUUID().toString();
            Map<String, Object> userData = new HashMap<>();
            userData.put("id", admin.get().getId());
            userData.put("login", admin.get().getEmail());
            userData.put("nome", admin.get().getNome());
            userData.put("tipo", "ADMIN"); 
            userData.put("token", token);

            return ResponseEntity.ok(userData);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(criarResposta(false, "Email ou senha inválidos", null, null, null));
    }

    /**
     * Endpoint para login exclusivo de estudantes.
     *
     * Autentica um estudante usando o e-mail e senha.
     * Retorna os dados do estudante autenticado com um token de acesso mockado.
     *
     * @param loginRequest Objeto {@link LoginRequest} contendo o e-mail e a senha do estudante.
     * @return {@code ResponseEntity} contendo um mapa com o status da operação, mensagem, tipo,
     *         dados do estudante e um token, e o status HTTP 200 (OK) em caso de sucesso.
     *         Retorna 400 (Bad Request) se e-mail ou senha forem inválidos ou vazios,
     *         ou 401 (Unauthorized) se as credenciais não forem válidas.
     */
    @Operation(summary = "Login exclusivo para estudantes (depreciado)", description = "Use o endpoint `/api/auth/login` unificado. Autentica um estudante e retorna seus dados com um token.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Login bem-sucedido"),
        @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    })
    @PostMapping("/login/estudante")
    public ResponseEntity<Map<String, Object>> loginEstudante(@RequestBody LoginRequest loginRequest) {

        if (loginRequest.getEmail() == null || loginRequest.getSenha() == null ||
                loginRequest.getEmail().isEmpty() || loginRequest.getSenha().isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(criarResposta(false, "Email e senha são obrigatórios", null, null, null));
        }

        Optional<Estudante> estudante = authService.autenticarEstudante(
                loginRequest.getEmail(),
                loginRequest.getSenha());

        if (estudante.isPresent()) {
            String token = "mock-token-" + UUID.randomUUID().toString();
            return ResponseEntity.ok(
                    criarResposta(true, "Login realizado com sucesso", "ESTUDANTE", estudante.get(), token));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(criarResposta(false, "Email ou senha inválidos", null, null, null));
        }
    }


    //Endpoint para login exclusivo de empresas.
    @Operation(summary = "Login exclusivo para empresas (depreciado)", description = "Use o endpoint `/api/auth/login` unificado. Autentica uma empresa e retorna seus dados com um token.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Login bem-sucedido"),
        @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    })
    @PostMapping("/login/empresa")
    public ResponseEntity<Map<String, Object>> loginEmpresa(@RequestBody LoginRequest loginRequest) {

        if (loginRequest.getEmail() == null || loginRequest.getSenha() == null ||
                loginRequest.getEmail().isEmpty() || loginRequest.getSenha().isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(criarResposta(false, "Email e senha são obrigatórios", null, null, null));
        }

        Optional<Empresa> empresa = authService.autenticarEmpresa(
                loginRequest.getEmail(),
                loginRequest.getSenha());

        if (empresa.isPresent()) {
            String token = "mock-token-" + UUID.randomUUID().toString();
            return ResponseEntity.ok(
                    criarResposta(true, "Login realizado com sucesso", "EMPRESA", empresa.get(), token));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(criarResposta(false, "Email ou senha inválidos", null, null, null));
        }
    }

    /**
     * Método auxiliar privado para criar um mapa de resposta padronizado.
     * Usado principalmente pelos endpoints de login depreciados.
     *
     * @param sucesso Indica se a operação foi bem-sucedida.
     * @param mensagem Uma mensagem descritiva do resultado.
     * @param tipo O tipo de usuário autenticado (ESTUDANTE, EMPRESA, ADMIN), se aplicável.
     * @param dados Os dados do objeto autenticado, se houver.
     * @param token O token de acesso gerado, se houver.
     * @return Um {@code Map<String, Object>} formatado como resposta da API.
     */
    private Map<String, Object> criarResposta(boolean sucesso, String mensagem, String tipo, Object dados,
            String token) {
        Map<String, Object> resposta = new HashMap<>();
        resposta.put("sucesso", sucesso);
        resposta.put("mensagem", mensagem);
        if (tipo != null) {
            resposta.put("tipo", tipo);
        }
        if (dados != null) {
            resposta.put("dados", dados);
        }
        if (token != null) {
            resposta.put("token", token);
        }
        return resposta;
    }
}