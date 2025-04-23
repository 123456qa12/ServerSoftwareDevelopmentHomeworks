package com.example.homework7.model;

import jakarta.persistence.*;

@Entity
@Table(name = "cash")
@PrimaryKeyJoinColumn(name = "payment_id")
public class Cash extends Payment {
	
	@Column(name = "cash_tendered", nullable = false)
    private float cashTendered;

    public float getCashTendered() { return cashTendered; }
    public void setCashTendered(float cashTendered) { this.cashTendered = cashTendered; }
}