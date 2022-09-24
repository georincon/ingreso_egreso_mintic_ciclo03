package com.upjavago.proyecto_ciclo03.controller;

import com.upjavago.proyecto_ciclo03.entity.Empresa;
import com.upjavago.proyecto_ciclo03.service.IEmpresaService;
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
public class EmpresaController {

    @Autowired
    private IEmpresaService empresaService;

    private final Logger LOG = Logger.getLogger(""+EmpresaController.class);
    private Validator validator;

    @GetMapping("/empresas/listar")
    public String findEmpresas(Model modelo) {
        LOG.log(Level.INFO,"findEmpresas");
        List<Empresa> empresas = empresaService.buscarTodasLasEmpresas();
        modelo.addAttribute("empresas", empresas);
        return "empresa/list";
    }

    @GetMapping("/empresas/crear")
    public String createEmpresa(Model modelo){
        LOG.log(Level.INFO,"createEmpresa");
        //Empresa
        Empresa empresa = new Empresa();
        modelo.addAttribute("empresa", empresa);
        return "empresa/modificar";
    }

    @PostMapping("/empresas/guardar")
    public String guardarEmpresa(@Valid Empresa emp, BindingResult error, Model modelo){
        LOG.log(Level.INFO,"guardarEmpresa");
        /*if(user.getRol().getIdRol() == 0) {
            String propertyPath = "violation.getPropertyPath().toString()";
            String message = "No puede ser nulll";
            FieldError field = new FieldError();
            error.addError(field);
        }*/
        for(ObjectError e : error.getAllErrors())
            System.out.println(e.toString());
        if(error.hasErrors()) {

            return "empresa/modificar";
        }
        emp.setEstado(true);
        emp = empresaService.crearEmpresa(emp);
        return "redirect:/empresas/listar";
    }

    @RequestMapping(value = "/empresas/editar/{id}", method = RequestMethod.GET)
    public String editEmpresa(@PathVariable("id") long id, Model modelo){
        LOG.log(Level.INFO,"editEmpresa");
        Empresa empresa = empresaService.buscarEmpresaPorId(id);
        modelo.addAttribute("empresa", empresa);
        return "empresa/modificar";
    }

    @RequestMapping(value = "/empresas/eliminar/{id}", method = RequestMethod.GET)
    public String deleteEmpresa(@PathVariable("id") long id, Model modelo) {
        LOG.log(Level.INFO, "deleteEmpresa");
        empresaService.eliminarEmpresa(id);
        return "redirect:/empresas/listar";
    }
}
