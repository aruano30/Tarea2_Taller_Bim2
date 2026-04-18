package com.arielruano.tienda.service;

import com.arielruano.tienda.entity.Pedido;

import java.util.List;

public interface PedidoService {
    List<Pedido> listar();
    Pedido obtenerPorId(Integer id);
    Pedido crear(Pedido pedido);
    Pedido actualizar(Integer idAct, Pedido pedido);
    void eliminar(Integer idElim);
}
