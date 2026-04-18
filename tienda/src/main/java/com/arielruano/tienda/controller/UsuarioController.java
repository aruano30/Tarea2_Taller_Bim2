package com.arielruano.tienda.controller;

import com.arielruano.tienda.entity.Usuario;
import com.arielruano.tienda.service.UsuarioService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Validated
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Método para listar los registros
    @GetMapping
    public String lista(Model model) {
        model.addAttribute("usuarios", usuarioService.listar());
        return "usuarios";
    }

    // Método para abrir una vista de formulario agregar
    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("modeEdicion", false);
        return "usuario-formulario";
    }

    // Metodo para abrir una vista de formulario actualizar
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") Integer id, Model model) {

        Usuario usuario = usuarioService.obtenerPorId(id);

        model.addAttribute("usuario", usuario);
        model.addAttribute("modeEdicion", true);

        return "usuario-formulario";
    }


    // Método para crear un nuevo usuario
    @PostMapping("/guardar")
    public String crear(@Valid @ModelAttribute("usuario") Usuario usuario, Model model, BindingResult result) {

        if (result.hasErrors()) {
            model.addAttribute("modeEdicion", false);
            return "usuario-formulario";
        }

        usuarioService.crear(usuario);
        return "redirect:/usuarios";
    }

    // Metodo actualizar un usuario
    @PostMapping("/actualizar")
    public String actualizar(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("modeEdicion", true);
            return "usuario-formulario";
        }

        usuarioService.actualizar(usuario.getIdUsuario(), usuario);
        return "redirect:/usuarios";
    }

    // Metodo para eliminar un usuario
    @GetMapping("eliminar/{id}")
    public String eliminar(@PathVariable("id") Integer id){

        usuarioService.eliminar(id);
        return "redirect:/usuarios";
    }

}