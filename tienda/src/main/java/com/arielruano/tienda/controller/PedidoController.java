package com.arielruano.tienda.controller;

import com.arielruano.tienda.entity.Pedido;
import com.arielruano.tienda.service.PedidoService;
import com.arielruano.tienda.service.UsuarioService;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@Validated
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;
    private final UsuarioService usuarioService;

    public PedidoController(PedidoService pedidoService, UsuarioService usuarioService) {
        this.pedidoService = pedidoService;
        this.usuarioService = usuarioService;
    }

    // Método para listar los registros
    @GetMapping
    public String lista(Model model) {
        model.addAttribute("pedidos", pedidoService.listar());
        return "pedidos";
    }

    // Método para abrir una vista de formulario agregar
    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("pedido", new Pedido());
        model.addAttribute("usuarios", usuarioService.listar());
        model.addAttribute("modeEdicion", false);
        return "pedido-formulario";
    }

    // Metodo para abrir una vista de formulario actualizar
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") Integer id, Model model) {

        Pedido pedido = pedidoService.obtenerPorId(id);

        model.addAttribute("pedido", pedido);
        model.addAttribute("usuarios", usuarioService.listar());
        model.addAttribute("modeEdicion", true);

        return "pedido-formulario";
    }

    // Método para crear un nuevo pedido
    @PostMapping("/guardar")
    public String crear(@Valid @ModelAttribute("pedido") Pedido pedido,
                        Model model,
                        BindingResult result) {

        if (result.hasErrors()) {
            model.addAttribute("usuarios", usuarioService.listar());
            model.addAttribute("modeEdicion", false);
            return "pedido-formulario";
        }

        pedidoService.crear(pedido);
        return "redirect:/pedidos";
    }

    // Metodo actualizar un pedido
    @PostMapping("/actualizar")
    public String actualizar(@Valid @ModelAttribute("pedido") Pedido pedido,
                             BindingResult result,
                             Model model) {

        if (result.hasErrors()) {
            model.addAttribute("usuarios", usuarioService.listar());
            model.addAttribute("modeEdicion", true);
            return "pedido-formulario";
        }

        pedidoService.actualizar(pedido.getIdPedido(), pedido);
        return "redirect:/pedidos";
    }

    // Metodo para eliminar un pedido
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Integer id) {

        pedidoService.eliminar(id);
        return "redirect:/pedidos";
    }
}