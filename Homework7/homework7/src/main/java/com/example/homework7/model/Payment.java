package com.example.homework7.model;

public class Payment{
	private float amount;
	private Order order;
	
	public float getAmount() { return amount; }
    public void setAmount(float amount) { this.amount = amount; }
	
	public Order getOrder() { return order; }
    public void setOrder(Order order) { this.order = order; }
}