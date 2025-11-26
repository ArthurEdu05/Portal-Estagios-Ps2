package br.mackenzie.ps2.portalestagios.repository;

import br.mackenzie.ps2.portalestagios.entities.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface empresaRepository extends JpaRepository<Empresa, Long> {

    // Método para buscar empresa por email
    Optional<Empresa> findByEmail(String email);
}