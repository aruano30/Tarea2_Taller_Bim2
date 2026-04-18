package com.arielruano.tienda.service;

import com.arielruano.tienda.entity.Usuario;

import java.util.List;

public interface UsuarioService {
    List<Usuario> listar();
    Usuario obtenerPorId(Integer id);
    Usuario crear(Usuario usuario);
    Usuario actualizar(Integer idAct, Usuario usuario);
    void eliminar(Integer idElim);
}
