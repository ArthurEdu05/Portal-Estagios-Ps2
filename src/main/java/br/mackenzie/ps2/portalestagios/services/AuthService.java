package br.mackenzie.ps2.portalestagios.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.mackenzie.ps2.portalestagios.entities.Estudante;
import br.mackenzie.ps2.portalestagios.entities.Empresa;
import br.mackenzie.ps2.portalestagios.repository.estudanteRepository;
import br.mackenzie.ps2.portalestagios.repository.empresaRepository;
import br.mackenzie.ps2.portalestagios.entities.Administrador;
import br.mackenzie.ps2.portalestagios.repository.administradorRepository;

import java.util.Optional;

/**
 * Serviço responsável pela lógica de negócio da autenticação de usuários.
 *
 * Esta classe centraliza as operações de validação de credenciais para os diferentes
 * tipos de usuários do sistema (Estudante, Empresa, Administrador). Ela interage
 * com os respectivos repositórios para buscar os usuários e verificar se as senhas
 * correspondem, atuando como uma camada intermediária entre o AuthController
 * e a camada de acesso a dados
 */
@Service
public class AuthService {

    @Autowired
    private estudanteRepository estudanteRep;

    @Autowired
    private empresaRepository empresaRep;

    @Autowired
    private administradorRepository adminRep;

    /**
     * Autentica um estudante com base no e-mail e senha fornecidos.
     *
     * @param email O e-mail do estudante a ser autenticado.
     * @param senha A senha do estudante.
     * @return Um {@code Optional<Estudante>} contendo o estudante se a autenticação for bem-sucedida,
     *         ou um {@code Optional} vazio caso contrário.
     */
    public Optional<Estudante> autenticarEstudante(String email, String senha) {
        Optional<Estudante> estudante = estudanteRep.findByEmail(email);

        if (estudante.isPresent() && estudante.get().getSenha().equals(senha)) {
            return estudante;
        }

        return Optional.empty();
    }

    /**
     * Autentica uma empresa com base no e-mail e senha fornecidos.
     *
     * @param email O e-mail da empresa a ser autenticada.
     * @param senha A senha da empresa.
     * @return Um {@code Optional<Empresa>} contendo a empresa se a autenticação for bem-sucedida,
     *         ou um {@code Optional} vazio caso contrário.
     */
    public Optional<Empresa> autenticarEmpresa(String email, String senha) {
        Optional<Empresa> empresa = empresaRep.findByEmail(email);

        if (empresa.isPresent() && empresa.get().getSenha().equals(senha)) {
            return empresa;
        }

        return Optional.empty();
    }

    /**
     * Autentica um administrador com base no e-mail e senha fornecidos.
     *
     * @param email O e-mail do administrador a ser autenticado.
     * @param senha A senha do administrador.
     * @return Um {@code Optional<Administrador>} contendo o administrador se a autenticação for bem-sucedida,
     *         ou um {@code Optional} vazio caso contrário.
     */
    public Optional<Administrador> autenticarAdministrador(String email, String senha) {
        Optional<Administrador> admin = adminRep.findByEmail(email);

        if (admin.isPresent() && admin.get().getSenha().equals(senha)) {
            return admin;
        }

        return Optional.empty();
    }
}