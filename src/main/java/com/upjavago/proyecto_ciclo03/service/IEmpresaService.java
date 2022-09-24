package com.upjavago.proyecto_ciclo03.service;

import com.upjavago.proyecto_ciclo03.entity.Empresa;

import java.util.List;

public interface IEmpresaService {

    public Empresa buscarEmpresaPorId(Long id);

    public List<Empresa> buscarTodasLasEmpresas();

    public Empresa crearEmpresa(Empresa empresa);

    public Empresa actualizarEmpresa(Long id, Empresa empresa);

    public void eliminarEmpresa(Long id);
}
