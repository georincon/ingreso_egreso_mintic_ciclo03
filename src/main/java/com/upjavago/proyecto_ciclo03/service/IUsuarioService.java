package com.upjavago.proyecto_ciclo03.service;

import com.upjavago.proyecto_ciclo03.entity.Usuario;

import java.util.List;

public interface IUsuarioService {

    public Usuario buscarUsuarioPorId(Long id);

    public List<Usuario> buscarTodosLosUsuarios();

    public Usuario crearUsuario(Usuario usuario);

    public Usuario actualizarUsuario(Long id, Usuario usuario);

    public void eliminarUsuario(Long id);


}
