package com.arielruano.tienda.service;

import com.arielruano.tienda.entity.DetallePedido;

import java.util.List;

public interface DetallePedidoService {
    List<DetallePedido> listar();
    DetallePedido obtenerPorId(Integer id);
    DetallePedido crear(DetallePedido detalle);
    DetallePedido actualizar(Integer idAct, DetallePedido detalle);
    void eliminar(Integer idElim);

}
