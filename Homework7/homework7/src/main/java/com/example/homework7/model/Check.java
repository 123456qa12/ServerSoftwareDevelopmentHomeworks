package com.example.homework7.model;

import jakarta.persistence.*;

@Entity
@Table(name = "payment_check")
@PrimaryKeyJoinColumn(name = "payment_id")
public class Check extends Payment {
	
	@Column(nullable = false)
    private String name;
	
	@Column(name = "bank_id", nullable = false)
    private String bankID;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getBankID() { return bankID; }
    public void setBankID(String bankID) { this.bankID = bankID; }
} 