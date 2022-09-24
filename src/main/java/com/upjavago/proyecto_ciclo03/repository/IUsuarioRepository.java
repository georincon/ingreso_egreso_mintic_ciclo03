package com.upjavago.proyecto_ciclo03.repository;

import com.upjavago.proyecto_ciclo03.entity.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface IUsuarioRepository extends CrudRepository<Usuario, Long> {

    Usuario findByUsername(String username);
}
