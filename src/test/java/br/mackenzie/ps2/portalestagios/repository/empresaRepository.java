package br.mackenzie.ps2.portalestagios.repository;

import br.mackenzie.ps2.portalestagios.entities.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface empresaRepository extends JpaRepository<Empresa, Long> {
}
