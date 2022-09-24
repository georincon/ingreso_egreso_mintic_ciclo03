package com.upjavago.proyecto_ciclo03.repository;

import com.upjavago.proyecto_ciclo03.entity.Movimiento;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface IMovimientoRepository extends CrudRepository<Movimiento, Long> {
}
