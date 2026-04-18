package com.arielruano.tienda.service;

import com.arielruano.tienda.entity.DetallePedido;
import com.arielruano.tienda.repository.DetallePedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetallePedidoServiceImplement implements DetallePedidoService{
    private final DetallePedidoRepository detallePedidoRepository;

    public DetallePedidoServiceImplement(DetallePedidoRepository detallePedidoRepository) {
        this.detallePedidoRepository = detallePedidoRepository;
    }

    @Override
    public List<DetallePedido> listar() {
        return detallePedidoRepository.findAll();
    }

    @Override
    public DetallePedido obtenerPorId(Integer id) {
        return detallePedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("DetallePedido no encontrado con Id: " + id));
    }

    @Override
    public DetallePedido crear(DetallePedido detalle) {
        detalle.setIdDetalle(null);
        return detallePedidoRepository.save(detalle);
    }

    @Override
    public DetallePedido actualizar(Integer idAct, DetallePedido detalle) {
        DetallePedido existente = obtenerPorId(idAct);
        existente.setCantidad(detalle.getCantidad());
        existente.setPrecioUnitario(detalle.getPrecioUnitario());
        existente.setPedido(detalle.getPedido());
        existente.setProducto(detalle.getProducto());
        return detallePedidoRepository.save(existente);
    }

    @Override
    public void eliminar(Integer idElim) {
        DetallePedido existente = obtenerPorId(idElim);
        detallePedidoRepository.delete(existente);
    }
}
