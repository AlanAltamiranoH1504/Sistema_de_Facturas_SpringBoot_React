package com.example.api_sistema_de_facturas.models;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_invoice_line")
public class InvoiceLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_invoice_line;
    private double amount;
    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_invoice")
    private Invoice invoice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_product")
    private Product product;

    public InvoiceLine() {
    }

    public InvoiceLine(double amount, int quantity, Invoice invoice, Product product) {
        this.amount = amount;
        this.quantity = quantity;
        this.invoice = invoice;
        this.product = product;
    }

    public long getId_invoice_line() {
        return id_invoice_line;
    }

    public void setId_invoice_line(long id_invoice_line) {
        this.id_invoice_line = id_invoice_line;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
