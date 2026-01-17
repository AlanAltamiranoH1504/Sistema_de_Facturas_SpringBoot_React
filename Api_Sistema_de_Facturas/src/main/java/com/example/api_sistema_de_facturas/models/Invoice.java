package com.example.api_sistema_de_facturas.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tbl_invoices")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_invoice;
    private double total;
    private String folio;
    private String descripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_client")
    private Client client;

    @JsonManagedReference
    @OneToMany(mappedBy = "invoice", fetch = FetchType.LAZY)
    private List<InvoiceLine> invoiceLines;

    public Invoice() {
    }

    public Invoice(double total, String folio, String descripcion, Client client) {
        this.total = total;
        this.folio = folio;
        this.descripcion = descripcion;
        this.client = client;
    }

    public Invoice(long id_invoice, double total, String folio, String descripcion, Client client) {
        this.id_invoice = id_invoice;
        this.total = total;
        this.folio = folio;
        this.descripcion = descripcion;
        this.client = client;
    }

    public long getId_invoice() {
        return id_invoice;
    }

    public void setId_invoice(long id_invoice) {
        this.id_invoice = id_invoice;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<InvoiceLine> getInvoiceLines() {
        return invoiceLines;
    }

    public void setInvoiceLines(List<InvoiceLine> invoiceLines) {
        this.invoiceLines = invoiceLines;
    }
}
