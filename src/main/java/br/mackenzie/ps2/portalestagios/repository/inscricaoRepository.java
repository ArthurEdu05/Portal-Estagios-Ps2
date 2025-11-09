package br.mackenzie.ps2.portalestagios.repository;

import br.mackenzie.ps2.portalestagios.entities.Inscricao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface inscricaoRepository extends JpaRepository<Inscricao, Long> {
}
