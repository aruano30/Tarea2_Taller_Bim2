package com.arielruano.tienda.service;

import com.arielruano.tienda.entity.Categoria;

import java.util.List;

public interface CategoriaService {
    List<Categoria> listar();
    Categoria obtenerPorId(Integer id);
    Categoria crear(Categoria categoria);
    Categoria actualizar(Integer idAct, Categoria categoria);
    void eliminar(Integer idElim);
}
