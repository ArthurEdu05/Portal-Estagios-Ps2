package br.mackenzie.ps2.portalestagios.repository;

import br.mackenzie.ps2.portalestagios.entities.Estudante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface estudanteRepository extends JpaRepository<Estudante, Long> {
}
