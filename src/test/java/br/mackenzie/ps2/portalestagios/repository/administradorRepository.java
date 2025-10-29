package br.mackenzie.ps2.portalestagios.repository;

import br.mackenzie.ps2.portalestagios.entities.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface administradorRepository extends JpaRepository<Administrador, Long> {
}
