package com.example.homework7.model;

import jakarta.persistence.*;

@Entity
@Table(name = "item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @ManyToOne
    @JoinColumn(name = "shipping_weight_id", nullable = false)
    private Weight shoppingWeight;
    
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    
    public Weight getShoppingWeight() { return shoppingWeight; }
    public void setShoppingWeight(Weight shoppingWeight) { 
        this.shoppingWeight = shoppingWeight; 
    }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { 
        this.description = description; 
    }
}