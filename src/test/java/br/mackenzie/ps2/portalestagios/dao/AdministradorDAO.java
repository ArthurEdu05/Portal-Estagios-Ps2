package br.mackenzie.ps2.portalestagios.dao;

import br.mackenzie.ps2.portalestagios.model.Administrador;
import org.springframework.data.repository.CrudRepository;

public interface AdministradorDAO extends CrudRepository  <Administrador, Long> {
}
