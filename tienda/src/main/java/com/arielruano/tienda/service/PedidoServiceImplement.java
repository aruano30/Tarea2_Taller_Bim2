package com.arielruano.tienda.service;

import com.arielruano.tienda.entity.Pedido;
import com.arielruano.tienda.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoServiceImplement implements PedidoService{
    private final PedidoRepository pedidoRepository;

    public PedidoServiceImplement(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public List<Pedido> listar() {
        return pedidoRepository.findAll();
    }

    @Override
    public Pedido obtenerPorId(Integer id) {
        return pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido no encontrado con Id: " + id));
    }

    @Override
    public Pedido crear(Pedido pedido) {
        pedido.setIdPedido(null);
        return pedidoRepository.save(pedido);
    }

    @Override
    public Pedido actualizar(Integer idAct, Pedido pedido) {
        Pedido existente = obtenerPorId(idAct);

        existente.setFechaPedido(pedido.getFechaPedido());
        existente.setTotalPedido(pedido.getTotalPedido());
        existente.setUsuario(pedido.getUsuario());

        return pedidoRepository.save(existente);
    }

    @Override
    public void eliminar(Integer idElim) {
        Pedido existente = obtenerPorId(idElim);
        pedidoRepository.delete(existente);
    }
}
