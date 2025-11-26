package br.mackenzie.ps2.portalestagios.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.mackenzie.ps2.portalestagios.entities.Estudante;
import br.mackenzie.ps2.portalestagios.entities.Empresa;
import br.mackenzie.ps2.portalestagios.repository.estudanteRepository;
import br.mackenzie.ps2.portalestagios.repository.empresaRepository;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private estudanteRepository estudanteRep;

    @Autowired
    private empresaRepository empresaRep;

    public Optional<Estudante> autenticarEstudante(String email, String senha) {
        Optional<Estudante> estudante = estudanteRep.findByEmail(email);

        if (estudante.isPresent() && estudante.get().getSenha().equals(senha)) {
            return estudante;
        }

        return Optional.empty();
    }


    public Optional<Empresa> autenticarEmpresa(String email, String senha) {
        Optional<Empresa> empresa = empresaRep.findByEmail(email);

        if (empresa.isPresent() && empresa.get().getSenha().equals(senha)) {
            return empresa;
        }

        return Optional.empty();
    }
}