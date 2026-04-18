package com.arielruano.tienda.service;

import com.arielruano.tienda.entity.Categoria;
import com.arielruano.tienda.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaServiceImplement implements CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public CategoriaServiceImplement(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public List<Categoria> listar() {
        return categoriaRepository.findAll();
    }

    @Override
    public Categoria obtenerPorId(Integer id) {
        return categoriaRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
    }

    @Override
    public Categoria crear(Categoria categoria) {
        categoria.setIdCategoria(null);
        return categoriaRepository.save(categoria);
    }

    @Override
    public Categoria actualizar(Integer idAct, Categoria categoria) {
        Categoria existenete = obtenerPorId(idAct);

        existenete.setNombreCategoria(categoria.getNombreCategoria());
        existenete.setDescripcionCategoria(categoria.getDescripcionCategoria());

        return categoriaRepository.save(existenete);
    }

    @Override
    public void eliminar(Integer idElim) {

        if (!categoriaRepository.existsById(idElim)) {
            return; // evita el error
        }

        categoriaRepository.deleteById(idElim);
    }
}
