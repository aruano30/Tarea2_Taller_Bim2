package com.arielruano.tienda.entity;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@JsonPropertyOrder({
        "idPedido",
        "fechaPedido",
        "totalPedido",
        "usuario"
})
@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private Integer idPedido;

    @NotNull(message = "La fecha del pedido no puede ser nula.")
    @PastOrPresent(message = "La fecha del pedido no puede ser futura.")
    @Column(name = "fecha_pedido")
    private LocalDate fechaPedido;

    @NotNull(message = "El total del pedido no puede ser nulo.")
    @DecimalMin(value = "0.01", message = "El total debe ser mayor a 0.")
    @Digits(integer = 10, fraction = 2, message = "El total debe tener máximo 2 decimales.")
    @Column(name = "total_pedido")
    private BigDecimal totalPedido;

    @NotNull(message = "El usuario es obligatorio.")
    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    // Getters and Setters

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public LocalDate getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(LocalDate fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public BigDecimal getTotalPedido() {
        return totalPedido;
    }

    public void setTotalPedido(BigDecimal totalPedido) {
        this.totalPedido = totalPedido;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}