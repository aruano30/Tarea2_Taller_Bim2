package com.arielruano.tienda.repository;

import com.arielruano.tienda.entity.DetallePedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Integer> {

    @Query("SELECT d FROM DetallePedido d " +
            "JOIN FETCH d.pedido " +
            "JOIN FETCH d.producto")
    List<DetallePedido> listarCompleto();
}