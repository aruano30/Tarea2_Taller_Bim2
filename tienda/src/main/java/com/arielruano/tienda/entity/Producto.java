package com.arielruano.tienda.entity;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

@JsonPropertyOrder({
        "idProducto",
        "nombreProducto",
        "precioProducto",
        "stock",
        "categoria"
})
@Entity
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Integer idProducto;

    @NotBlank(message = "El nombre del producto no puede ir vacío.")
    @Size(min = 2, max = 80, message = "Debe tener entre 2 y 80 caracteres.")
    @Column(name = "nombre_producto")
    private String nombreProducto;

    @NotNull(message = "El precio no puede ir vacío.")
    @DecimalMin(value = "0.01", message = "El precio debe ser mayor a 0.")
    @Column(name = "precio_producto")
    private BigDecimal precioProducto;

    @NotNull(message = "El stock no puede ir vacío.")
    @Min(value = 0, message = "El stock no puede ser negativo.")
    @Column(name = "stock")
    private Integer stock;

    @NotNull(message = "Debe seleccionar una categoría.")
    @ManyToOne
    @JoinColumn(name = "id_categoria", nullable = false)
    private Categoria categoria;

    // Getters y Setters

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public BigDecimal getPrecioProducto() {
        return precioProducto;
    }

    public void setPrecioProducto(BigDecimal precioProducto) {
        this.precioProducto = precioProducto;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}