package br.mackenzie.ps2.portalestagios.repository;

import br.mackenzie.ps2.portalestagios.entities.Estudante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface estudanteRepository extends JpaRepository<Estudante, Long> {

    // Método para buscar estudante por email
    Optional<Estudante> findByEmail(String email);
}