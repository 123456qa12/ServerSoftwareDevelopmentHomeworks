package com.example.homework7.model;

import jakarta.persistence.*;

@Entity
@Table(name = "quantity")
public class Quantity extends Measurement {

	@Column(nullable = false)
    private int value;

    public int getValue() { return value; }
    public void setValue(int value) { this.value = value; }
}