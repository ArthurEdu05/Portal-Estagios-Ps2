package br.mackenzie.ps2.portalestagios.dao;

import br.mackenzie.ps2.portalestagios.model.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioDAO extends CrudRepository<Usuario, Long> {
}
