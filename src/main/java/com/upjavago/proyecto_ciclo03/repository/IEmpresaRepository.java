package com.upjavago.proyecto_ciclo03.repository;

import com.upjavago.proyecto_ciclo03.entity.Empresa;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface IEmpresaRepository extends CrudRepository<Empresa, Long> {
}
