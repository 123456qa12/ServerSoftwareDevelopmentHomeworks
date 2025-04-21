package com.example.homework7.model;

import java.util.List;

public class OrderDetail{
	private Quantity quantity;
	private String taxStatus;
	private List<Item> items;
	
	public Quantity getQuantity() { return quantity; }
    public void setQuantity(Quantity quantity) { this.quantity = quantity; }
	
	public String getTaxStatus() { return taxStatus; }
    public void setTaxStatus(String taxStatus) { this.taxStatus = taxStatus; }
	
	public List<Item> Items() { return items; }
    public void setItems(List<Item> items) { this.items = items; }
}