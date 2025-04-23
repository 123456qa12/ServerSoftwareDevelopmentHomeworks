package com.example.homework7.model;

import jakarta.persistence.*;

@Entity
@Table(name = "payment")
@Inheritance(strategy = InheritanceType.JOINED)
public class Payment{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(nullable = false)
	private float amount;
	
	@ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
	private Order order;
	
	public float getAmount() { return amount; }
    public void setAmount(float amount) { this.amount = amount; }
	
	public Order getOrder() { return order; }
    public void setOrder(Order order) { this.order = order; }
}