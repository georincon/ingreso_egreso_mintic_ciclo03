package com.upjavago.proyecto_ciclo03.service;

import com.upjavago.proyecto_ciclo03.entity.Empresa;
import com.upjavago.proyecto_ciclo03.repository.IEmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpresaServiceImpl implements IEmpresaService{

    @Autowired
    private IEmpresaRepository empresaRepository;

    @Override
    public Empresa buscarEmpresaPorId(Long id) {
        Optional<Empresa> empresa = empresaRepository.findById(id);
        return empresa.get();
    }

    @Override
    public List<Empresa> buscarTodasLasEmpresas() {
        List<Empresa> empresas = (List<Empresa>) empresaRepository.findAll();
        return empresas;
    }

    @Override
    public Empresa crearEmpresa(Empresa empresa) {
        return empresaRepository.save(empresa);
    }

    @Override
    public Empresa actualizarEmpresa(Long id, Empresa empresa) {
        Empresa empresaDB = buscarEmpresaPorId(id);
        if (null == empresaDB) {
            return null;
        }
        empresaDB.setNombre(empresa.getNombre());
        empresaDB.setDireccion(empresa.getDireccion());
        empresaDB.setTelefono(empresa.getTelefono());
        empresaDB.setNit(empresa.getNit());
        return empresaRepository.save(empresaDB);
    }

    @Override
    public void eliminarEmpresa(Long id) {
        empresaRepository.deleteById(id);
    }
}
