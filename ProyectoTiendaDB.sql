drop database if exists Tarea_Tienda_db_in5bv;
create database Tarea_Tienda_db_in5bv;
use Tarea_Tienda_db_in5bv;
 
create table usuario(
	id_usuario int auto_increment not null primary key,
    nombre_usuario varchar(60) not null,
    apellido_usuario varchar(60) not null,
    edad_usuario int not null,
	username varchar(60) unique,
    password varchar(255)
);

insert into usuario (nombre_usuario, apellido_usuario, edad_usuario) values
('Juan', 'Pérez', 25),
('María', 'López', 30),
('Carlos', 'Gómez', 22),
('Ana', 'Martínez', 28),
('Luis', 'Hernández', 35),
('Sofía', 'Ramírez', 27),
('Pedro', 'Torres', 40),
('Lucía', 'Flores', 19),
('Diego', 'Morales', 33),
('Elena', 'Castro', 26);

create table categoria(
	id_categoria int auto_increment not null primary key,
    nombre_categoria varchar(60) not null,
    descripcion_categoria varchar(150)
);

insert into categoria (nombre_categoria, descripcion_categoria) values
('Electrónica', 'Dispositivos electrónicos y tecnología'),
('Ropa', 'Prendas de vestir para todas las edades'),
('Hogar', 'Artículos para el hogar y decoración'),
('Deportes', 'Equipos y accesorios deportivos'),
('Alimentos', 'Productos comestibles y bebidas'),
('Juguetes', 'Juguetes para niños y entretenimiento'),
('Libros', 'Libros educativos y de entretenimiento'),
('Belleza', 'Productos de cuidado personal y estética'),
('Automotriz', 'Accesorios y repuestos para vehículos'),
('Mascotas', 'Productos para el cuidado de animales');
 
create table producto(
	id_producto int auto_increment not null primary key,
    nombre_producto varchar(80) not null,
    precio_producto decimal(10,2) not null,
    stock int not null,
    id_categoria int not null,
    constraint fk_producto_categoria
        foreign key (id_categoria)
        references categoria(id_categoria)
);

insert into producto (nombre_producto, precio_producto, stock, id_categoria) values
('Smartphone Samsung', 2999.99, 15, 1),
('Laptop Lenovo', 5999.99, 8, 1),
('Camiseta Deportiva', 149.99, 50, 2),
('Sofá 3 plazas', 3499.99, 5, 3),
('Bicicleta Montaña', 2499.99, 10, 4),
('Cereal Integral', 45.50, 100, 5),
('Muñeca Barbie', 199.99, 20, 6),
('Libro Java Básico', 180.00, 30, 7),
('Crema Facial', 120.00, 40, 8),
('Juguete para Perro', 95.00, 25, 10);

create table pedido(
	id_pedido int auto_increment not null primary key,
    fecha_pedido varchar(60) not null,
    total_pedido decimal(10,2) not null,
    id_usuario int not null,
    constraint fk_pedido_usuario
        foreign key (id_usuario)
        references usuario(id_usuario)
);

insert into pedido (fecha_pedido, total_pedido, id_usuario) values
('2026-04-01', 2999.99, 1),
('2026-04-02', 5999.99, 2),
('2026-04-03', 149.99, 3),
('2026-04-04', 3499.99, 4),
('2026-04-05', 2499.99, 5),
('2026-04-06', 45.50, 6),
('2026-04-07', 199.99, 7),
('2026-04-08', 180.00, 8),
('2026-04-09', 120.00, 9),
('2026-04-10', 95.00, 10);

 
create table detalle_pedido(
	id_detalle int auto_increment not null primary key,
    cantidad int not null,
    precio_unitario decimal(10,2) not null,
    id_pedido int not null,
    id_producto int not null,
    constraint fk_detalle_pedido
        foreign key (id_pedido)
        references pedido(id_pedido),
    constraint fk_detalle_producto
        foreign key (id_producto)
        references producto(id_producto)
);

insert into detalle_pedido (cantidad, precio_unitario, id_pedido, id_producto) values
(1, 2999.99, 1, 1),
(1, 5999.99, 2, 2),
(2, 149.99, 3, 3),
(1, 3499.99, 4, 4),
(1, 2499.99, 5, 5),
(3, 45.50, 6, 6),
(1, 199.99, 7, 7),
(1, 180.00, 8, 8),
(2, 120.00, 9, 9),
(1, 95.00, 10, 10);


-- select * from usuario;
-- select * from categoria;
-- select * from producto;
-- select * from pedido;
-- select * from detalle_pedido;
