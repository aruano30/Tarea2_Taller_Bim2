package com.arielruano.tienda.service;

import com.arielruano.tienda.entity.Usuario;
import com.arielruano.tienda.repository.UsuarioRepository;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class UsuarioServiceImplement implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImplement(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario obtenerPorId(Integer id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
    }

    @Override
    public Usuario crear(Usuario usuario) {
         usuario.setIdUsuario(null);
         return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario actualizar(Integer idAct, Usuario usuario) {
        Usuario existente = obtenerPorId(idAct);

        existente.setNombreUsuario(usuario.getNombreUsuario());
        existente.setApellidoUsuario(usuario.getApellidoUsuario());
        existente.setEdadUsuario(usuario.getEdadUsuario());

        return usuarioRepository.save(existente);
    }

    @Override
    public void eliminar(Integer idElim) {
        Usuario existente = obtenerPorId(idElim);

        usuarioRepository.delete(existente);

    }
}
