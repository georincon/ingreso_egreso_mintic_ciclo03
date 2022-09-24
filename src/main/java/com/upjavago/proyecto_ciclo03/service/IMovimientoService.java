package com.upjavago.proyecto_ciclo03.service;

import com.upjavago.proyecto_ciclo03.entity.Movimiento;

import java.util.List;

public interface IMovimientoService {

    public Movimiento buscarMovimientoPorId(Long id);

    public List<Movimiento> buscarTodosLosMovimientos();

    public Movimiento crearMovimiento(Movimiento movimiento);

    public Movimiento actualizarMovimiento(Long id, Movimiento movimiento);

    public void eliminarMovimiento(Long id);

}
