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

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthService authService;

    /**
     * Endpoint unificado de login (frontend usa este)
     * POST /api/auth/login
     */
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