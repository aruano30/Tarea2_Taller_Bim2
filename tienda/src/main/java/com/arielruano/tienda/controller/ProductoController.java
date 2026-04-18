package com.arielruano.tienda.controller;

import com.arielruano.tienda.entity.Producto;
import com.arielruano.tienda.service.ProductoService;
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
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService productoService;
    private final CategoriaService categoriaService;

    public ProductoController(ProductoService productoService, CategoriaService categoriaService) {
        this.productoService = productoService;
        this.categoriaService = categoriaService;
    }

    // Metodo para listar registros
    @GetMapping
    public String listar(Model model){
        model.addAttribute("productos", productoService.listar());
        return "productos";
    }

    // Método para abrir una vista de formulario agregar
    @GetMapping("/nuevo")
    public String nuevo(Model model){
        model.addAttribute("producto", new Producto());
        model.addAttribute("categorias", categoriaService.listar());
        model.addAttribute("modeEdicion", false);
        return "producto-formulario";
    }

    // Metodo para abrir una vista de formulario actualizar
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model){
        model.addAttribute("producto", productoService.obtenerPorId(id));
        model.addAttribute("categorias", categoriaService.listar());
        model.addAttribute("modeEdicion", true);
        return "producto-formulario";
    }

    // Método para crear un nuevo producto
    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute Producto producto, BindingResult result, Model model){

        if(result.hasErrors()){
            model.addAttribute("categorias", categoriaService.listar());
            model.addAttribute("modeEdicion", false);
            return "producto-formulario";
        }

        if(producto.getCategoria() == null || producto.getCategoria().getIdCategoria() == null){
            model.addAttribute("categorias", categoriaService.listar());
            model.addAttribute("modeEdicion", false);
            return "producto-formulario";
        }

        Integer idCat = producto.getCategoria().getIdCategoria();
        producto.setCategoria(categoriaService.obtenerPorId(idCat));

        productoService.crear(producto);

        return "redirect:/productos";
    }


    // Metodo actualizar un producto
    @PostMapping("/actualizar")
    public String actualizar(@Valid @ModelAttribute Producto producto, BindingResult result, Model model){

        if(result.hasErrors()){
            model.addAttribute("categorias", categoriaService.listar());
            model.addAttribute("modeEdicion", true);
            return "producto-formulario";
        }

        Integer idCat = producto.getCategoria().getIdCategoria();
        producto.setCategoria(categoriaService.obtenerPorId(idCat));

        productoService.actualizar(producto.getIdProducto(), producto);

        return "redirect:/productos";
    }

    // Metodo para eliminar un producto
    @GetMapping("eliminar/{id}")
    public String eliminar(@PathVariable Integer id){
        productoService.eliminar(id);
        return "redirect:/productos";
    }
}
