package com.arielruano.tienda.controller;

import com.arielruano.tienda.entity.DetallePedido;
import com.arielruano.tienda.service.DetallePedidoService;
import com.arielruano.tienda.service.PedidoService;
import com.arielruano.tienda.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@Validated
@RequestMapping("/detalle-pedidos")
public class DetallePedidoController {

    private final DetallePedidoService detallePedidoService;
    private final PedidoService pedidoService;
    private final ProductoService productoService;

    public DetallePedidoController(DetallePedidoService detallePedidoService,
                                   PedidoService pedidoService,
                                   ProductoService productoService) {
        this.detallePedidoService = detallePedidoService;
        this.pedidoService = pedidoService;
        this.productoService = productoService;
    }

    // Metodo para listar registros
    @GetMapping
    public String lista(Model model) {
        model.addAttribute("detalles", detallePedidoService.listar());
        return "detalle-pedidos";
    }

    // Método para abrir una vista de formulario agregar
    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("detalle", new DetallePedido());
        model.addAttribute("pedidos", pedidoService.listar());
        model.addAttribute("productos", productoService.listar());
        model.addAttribute("modeEdicion", false);
        return "detalle-pedido-formulario";
    }

    // Metodo para abrir una vista de formulario actualizar
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") Integer id, Model model) {

        DetallePedido detalle = detallePedidoService.obtenerPorId(id);

        model.addAttribute("detalle", detalle);
        model.addAttribute("pedidos", pedidoService.listar());
        model.addAttribute("productos", productoService.listar());
        model.addAttribute("modeEdicion", true);

        return "detalle-pedido-formulario";
    }

    // Método para crear un nuevo detalle-pedido
    @PostMapping("/guardar")
    public String crear(@Valid @ModelAttribute("detalle") DetallePedido detalle,
                        BindingResult result,
                        Model model) {

        if (result.hasErrors()) {
            model.addAttribute("pedidos", pedidoService.listar());
            model.addAttribute("productos", productoService.listar());
            model.addAttribute("modeEdicion", false);
            return "detalle-pedido-formulario";
        }

        detallePedidoService.crear(detalle);
        return "redirect:/detalle-pedidos";
    }

    // Metodo actualizar un detalle-producto
    @PostMapping("/actualizar")
    public String actualizar(@Valid @ModelAttribute("detalle") DetallePedido detalle,
                             BindingResult result,
                             Model model) {

        if (result.hasErrors()) {
            model.addAttribute("pedidos", pedidoService.listar());
            model.addAttribute("productos", productoService.listar());
            model.addAttribute("modeEdicion", true);
            return "detalle-pedido-formulario";
        }

        detallePedidoService.actualizar(detalle.getIdDetalle(), detalle);
        return "redirect:/detalle-pedidos";
    }

    // Metodo para eliminar un detalle-producto
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Integer id) {

        detallePedidoService.eliminar(id);
        return "redirect:/detalle-pedidos";
    }
}