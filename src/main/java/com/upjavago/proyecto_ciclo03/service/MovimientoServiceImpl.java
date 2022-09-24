package com.upjavago.proyecto_ciclo03.service;

import com.upjavago.proyecto_ciclo03.entity.Empresa;
import com.upjavago.proyecto_ciclo03.entity.Movimiento;
import com.upjavago.proyecto_ciclo03.repository.IMovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovimientoServiceImpl implements IMovimientoService{

    @Autowired
    private IMovimientoRepository movimientoRepository;

    @Override
    public Movimiento buscarMovimientoPorId(Long id) {
        Optional<Movimiento> movimiento = movimientoRepository.findById(id);
        return movimiento.get();
    }

    @Override
    public List<Movimiento> buscarTodosLosMovimientos() {
        List<Movimiento> movimientos = (List<Movimiento>) movimientoRepository.findAll();
        return movimientos;
    }

    @Override
    public Movimiento crearMovimiento(Movimiento movimiento) {
        Movimiento newMovimiento = movimientoRepository.save(movimiento);
        return newMovimiento;
    }

    @Override
    public Movimiento actualizarMovimiento(Long id, Movimiento movimiento) {
        Movimiento movimientoDB = buscarMovimientoPorId(id);
        if (null == movimientoDB) {
            return null;
        }
        movimientoDB.setMonto(movimiento.getMonto());
        movimientoDB.setTipoMovimiento(movimiento.getTipoMovimiento());
        movimientoDB.setConcepto(movimiento.getConcepto());
        movimientoDB.setUsuario(movimiento.getUsuario());
        return movimientoRepository.save(movimientoDB);

    }

    @Override
    public void eliminarMovimiento(Long id) {
        movimientoRepository.deleteById(id);
    }
}
