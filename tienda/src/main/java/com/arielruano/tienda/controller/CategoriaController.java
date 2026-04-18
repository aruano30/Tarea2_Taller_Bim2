package com.arielruano.tienda.controller;

import com.arielruano.tienda.entity.Categoria;
import com.arielruano.tienda.service.CategoriaService;
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
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    // Metodo para listar registros
    @GetMapping
    public String lista(Model model){
        model.addAttribute("categorias", categoriaService.listar());
        return"categorias";
    }

    // Metodo para abrir una vista de formulario agregar
    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model){
        model.addAttribute("categoria",  new Categoria());
        model.addAttribute("modeEdicion", false);
        return "categoria-formulario";
    }

    // Metodo para abrir una vista de formulario actualizar
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") Integer id, Model model){

        Categoria categoria = categoriaService.obtenerPorId(id);

        model.addAttribute("categoria", categoria);
        model.addAttribute("modeEdicion", true);

        return "categoria-formulario";
    }

    // Metodo para crear una categoria nueva
    @PostMapping("/guardar")
    public String crear(@Valid @ModelAttribute("categoria") Categoria categoria, BindingResult result, Model model){

        if (result.hasErrors()){
            model.addAttribute("modeEdicion", false);
            return "categoria-formulario";
        }

        categoriaService.crear(categoria);
        return "redirect:/categorias";
    }

    // Metodo para actualizar una categoria
    @PostMapping("/actualizar")
    public String actualizar(@Valid @ModelAttribute("categoria")Categoria categoria, BindingResult result, Model model){

        if (result.hasErrors()){
            model.addAttribute("modeEdicion", true);
            return "categoria-formulario";

        }

        categoriaService.actualizar(categoria.getIdCategoria(), categoria);
        return "redirect:/categorias";
    }

    // Metodo para eliminar una categoria
    @GetMapping("eliminar/{id}")
    public String eliminar(@PathVariable("id") Integer id){

        categoriaService.eliminar(id);
        return "redirect:/categorias";
    }
}
