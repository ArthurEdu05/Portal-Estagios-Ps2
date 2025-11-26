package br.mackenzie.ps2.portalestagios.repository;

import br.mackenzie.ps2.portalestagios.entities.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface administradorRepository extends JpaRepository<Administrador, Long> {
     Optional<Administrador> findByEmail(String email);
    
}
