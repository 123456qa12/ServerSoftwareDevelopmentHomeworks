package com.example.homework7.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "order_detail")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;  // Добавлено поле для связи
    
    @ManyToOne
    @JoinColumn(name = "quantity_id", nullable = false)
    private Quantity quantity;
    
    @Column(name = "tax_status", nullable = false)
    private String taxStatus;
    
    @ManyToMany
    @JoinTable(
        name = "order_detail_item",
        joinColumns = @JoinColumn(name = "order_detail_id"),
        inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private List<Item> items;

    // Геттеры и сеттеры
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Order getOrder() { return order; }
    public void setOrder(Order order) { this.order = order; }
    
    public Quantity getQuantity() { return quantity; }
    public void setQuantity(Quantity quantity) { this.quantity = quantity; }
    
    public String getTaxStatus() { return taxStatus; }
    public void setTaxStatus(String taxStatus) { this.taxStatus = taxStatus; }
    
    public List<Item> getItems() { return items; }
    public void setItems(List<Item> items) { this.items = items; }
}