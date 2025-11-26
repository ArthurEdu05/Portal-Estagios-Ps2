package br.mackenzie.ps2.portalestagios.repository;

import br.mackenzie.ps2.portalestagios.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface usuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByLogin(String login);
}
