package com.upjavago.proyecto_ciclo03.controller;

import com.upjavago.proyecto_ciclo03.entity.Empresa;
import com.upjavago.proyecto_ciclo03.entity.Rol;
import com.upjavago.proyecto_ciclo03.entity.Usuario;
import com.upjavago.proyecto_ciclo03.service.IEmpresaService;
import com.upjavago.proyecto_ciclo03.service.IRolService;
import com.upjavago.proyecto_ciclo03.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.Validator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IEmpresaService empresaService;

    @Autowired
    private IRolService rolService;

    private final Logger LOG = Logger.getLogger(""+UsuarioController.class);
    private Validator validator;

    @GetMapping("/usuarios/listar")
    public String findUsuarios(Model model) {
        LOG.log(Level.INFO,"findUsuarios");
        List<Usuario> usuarios = usuarioService.buscarTodosLosUsuarios();
        model.addAttribute("usuarios", usuarios);
        return "usuario/list";
    }

    @GetMapping("/usuarios/crear")
    public String createUsuario(Model modelo){
        LOG.log(Level.INFO,"createUsuario");
        //Usuario
        Usuario usuario = new Usuario();
        modelo.addAttribute("usuario", usuario);
        //Empresa
        List<Empresa> empresas = empresaService.buscarTodasLasEmpresas();
        modelo.addAttribute("empresas", empresas);
        //Rol
        List<Rol> roles = rolService.findAll();
        modelo.addAttribute("roles", roles);
        return "usuario/modificar";
    }

    @PostMapping("/usuarios/guardar")
    public String guardarUsuario(@Valid Usuario user, BindingResult error, Model modelo){
        LOG.log(Level.INFO,"guardarUsuario");
        /*if(user.getRol().getIdRol() == 0) {
            String propertyPath = "violation.getPropertyPath().toString()";
            String message = "No puede ser nulll";
            FieldError field = new FieldError();
            error.addError(field);
        }*/
        for(ObjectError e : error.getAllErrors())
            System.out.println(e.toString());
        if(error.hasErrors()) {

            return "usuario/modificar";
        }
        user.setEstado(true);
        user = usuarioService.crearUsuario(user);
        return "redirect:/usuarios/listar";
    }

    @RequestMapping(value = "/usuarios/editar/{id}", method = RequestMethod.GET)
    public String editUsuario(@PathVariable("id") long id, Model modelo){
        LOG.log(Level.INFO,"editUsuario");
        Usuario usuario = usuarioService.buscarUsuarioPorId(id);
        modelo.addAttribute("usuario", usuario);
        //Empresa
        List<Empresa> empresas = empresaService.buscarTodasLasEmpresas();
        modelo.addAttribute("empresas", empresas);
        //Rol
        List<Rol> roles = rolService.findAll();
        modelo.addAttribute("roles", roles);
        return "usuario/modificar";
    }

    @RequestMapping(value = "/usuarios/eliminar/{id}", method = RequestMethod.GET)
    public String deleteUsuario(@PathVariable("id") long id, Model modelo) {
        LOG.log(Level.INFO, "deleteUsuario");
        usuarioService.eliminarUsuario(id);
        return "redirect:/usuarios/listar";
    }
}