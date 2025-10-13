package br.mackenzie.ps2.portalestagios.dao;

import br.mackenzie.ps2.portalestagios.model.Empresa;
import org.springframework.data.repository.CrudRepository;

public interface EmpresaDAO extends CrudRepository<Empresa, Long> {
}
