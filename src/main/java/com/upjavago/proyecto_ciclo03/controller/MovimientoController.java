package com.upjavago.proyecto_ciclo03.controller;

import com.upjavago.proyecto_ciclo03.entity.Movimiento;
import com.upjavago.proyecto_ciclo03.entity.Usuario;
import com.upjavago.proyecto_ciclo03.service.IMovimientoService;
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
public class MovimientoController {

    @Autowired
    private IMovimientoService movimientoService;

    @Autowired
    private IUsuarioService usuarioService;

    private final Logger LOG = Logger.getLogger(""+MovimientoController.class);
    private Validator validator;

    @GetMapping("/movimientos/listar")
    public String findMovimientos(Model modelo) {
        LOG.log(Level.INFO,"findMovimientos");
        List<Movimiento> movimientos = movimientoService.buscarTodosLosMovimientos();
        modelo.addAttribute("movimientos", movimientos);
        return "movimiento/list";
    }

    @GetMapping("/movimientos/crear")
    public String createMovimiento(Model modelo){
        LOG.log(Level.INFO,"createMovimiento");
        //Movimiento
        Movimiento movimiento = new Movimiento();
        modelo.addAttribute("movimiento", movimiento);
        //Usuario
        List<Usuario> usuarios = usuarioService.buscarTodosLosUsuarios();
        modelo.addAttribute("usuarios", usuarios);
        return "movimiento/modificar";
    }

    @PostMapping("/movimientos/guardar")
    public String guardarMovimiento(@Valid Movimiento mov, BindingResult error, Model modelo){
        LOG.log(Level.INFO,"guardarMovimiento");
        /*if(user.getRol().getIdRol() == 0) {
            String propertyPath = "violation.getPropertyPath().toString()";
            String message = "No puede ser nulll";
            FieldError field = new FieldError();
            error.addError(field);
        }*/
        for(ObjectError e : error.getAllErrors())
            System.out.println(e.toString());
        if(error.hasErrors()) {

            return "movimiento/modificar";
        }
        mov.setEstado(true);
        mov = movimientoService.crearMovimiento(mov);
        return "redirect:/movimientos/listar";
    }

    @RequestMapping(value = "/movimientos/editar/{id}", method = RequestMethod.GET)
    public String editMovimiento(@PathVariable("id") long id, Model modelo){
        LOG.log(Level.INFO,"editMovimiento");
        Movimiento movimiento = movimientoService.buscarMovimientoPorId(id);
        modelo.addAttribute("movimiento", movimiento);
        //Usuario
        List<Usuario> usuarios = usuarioService.buscarTodosLosUsuarios();
        modelo.addAttribute("usuarios", usuarios);
        return "movimiento/modificar";
    }

    @RequestMapping(value = "/movimientos/eliminar/{id}", method = RequestMethod.GET)
    public String deleteMovimiento(@PathVariable("id") long id, Model modelo) {
        LOG.log(Level.INFO, "deleteMovimiento");
        movimientoService.eliminarMovimiento(id);
        return "redirect:/movimientos/listar";
    }
}