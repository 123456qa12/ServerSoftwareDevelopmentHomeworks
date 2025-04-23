package com.example.homework7.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "weight")
public class Weight extends Measurement {

	@Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal value;

    public BigDecimal getValue() { return value; }
    public void setValue(BigDecimal value) { this.value = value; }
}