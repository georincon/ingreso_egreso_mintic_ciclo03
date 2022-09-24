package com.upjavago.proyecto_ciclo03.service;

import com.upjavago.proyecto_ciclo03.entity.Rol;

import java.util.List;

public interface IRolService {

    public Rol findById(Long id);

    public List<Rol> findAll();

    public Rol createRol(Rol rol);

    public Rol updateRol(Long id, Rol rol);

    public void deleteRol(Long id);
}
