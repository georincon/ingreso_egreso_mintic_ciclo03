package com.upjavago.proyecto_ciclo03.service;

import com.upjavago.proyecto_ciclo03.entity.Usuario;
import com.upjavago.proyecto_ciclo03.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Override
    public Usuario buscarUsuarioPorId(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario.get();
    }

    @Override
    public List<Usuario> buscarTodosLosUsuarios() {
        List<Usuario> usuarios = (List<Usuario>) usuarioRepository.findAll();
        return usuarios;
    }

    @Override
    public Usuario crearUsuario(Usuario usuario) {
        Usuario newUsuario = usuarioRepository.save(usuario);
        return newUsuario;
    }

    @Override
    public Usuario actualizarUsuario(Long id, Usuario usuario) {
        Usuario usuarioDB = buscarUsuarioPorId(id);
        if (null == usuarioDB) {
            return null;
        }
        usuarioDB.setNombre(usuario.getNombre());
        usuarioDB.setCorreo(usuario.getCorreo());
        usuarioDB.setEmpresa(usuario.getEmpresa());
        usuarioDB.setRol(usuario.getRol());
        return usuarioRepository.save(usuarioDB);

    }

    @Override
    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}
