package com.arielruano.tienda.service;

import com.arielruano.tienda.entity.Producto;

import java.util.List;

public interface ProductoService {
    List<Producto> listar();
    Producto obtenerPorId(Integer id);
    Producto crear(Producto producto);
    Producto actualizar(Integer idAct, Producto producto);
    void eliminar(Integer idElim);


}
