-- SISTEMA DE FACTURAS SPRING BOOT Y REACT ENERO DE 2026

-- Creacion y uso de base de datos
CREATE DATABASE db_facturas_spring_boot;
USE db_facturas_spring_boot;

-- tbl_client (Un cliente puede tener n facturas)
DROP TABLE tbl_clients;
CREATE TABLE tbl_clients(
	id_client  bigint       NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name       varchar(255) NOT NULL,
    surnames   varchar(255) NULL,
    email      varchar(100) NOT NULL UNIQUE,
    address    text         NULL,
    created_at datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- tbl_invoice (Una factura pertenece a un cliente. Un factura puede tener n lineas de factura)
DROP TABLE tbl_invoices;
CREATE TABLE tbl_invoices(
	id_invoice bigint 	 NOT NULL PRIMARY KEY AUTO_INCREMENT,
    total      double 	 NOT NULL,
    id_client  bigint    NOT NULL,
    created_at datetime  NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at datetime  NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    -- Relacion con cliente
    FOREIGN KEY (id_client) REFERENCES tbl_clients(id_client)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);

-- tbl_products
DROP TABLE tbl_products;
CREATE TABLE tbl_products(
	id_product   bigint       NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name_product varchar(255) NOT NULL,
    description  text         NOT NULL,
    price        double       NOT NULL,
    sku          varchar(20)  NOT NULL UNIQUE,
    created_at datetime       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at datetime       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);


-- tbl_invoice_line (Un linea de factura pertene a una factura. Un linea de factura pertenece a un producto)
DROP TABLE tbl_invoice_line;
CREATE TABLE tbl_invoice_line(
	id_invoice_line bigint 		 NOT NULL PRIMARY KEY AUTO_INCREMENT,
    amount          double 		 NOT NULL,
    quantity        int    	 	 NOT NULL,
    id_invoice      bigint 		 NOT NULL,
    id_product      bigint 		 NOT NULL,
    created_at      datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at      datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    -- Relacion con producto
    FOREIGN KEY (id_product) REFERENCES tbl_products(id_product)
    ON UPDATE CASCADE 
    ON DELETE CASCADE,
    
    -- Relacion factura
    FOREIGN KEY (id_invoice) REFERENCES tbl_invoices(id_invoice)
    ON UPDATE CASCADE 
    ON DELETE CASCADE
);
