package com.example.homework7.model;

import jakarta.persistence.*;

@MappedSuperclass
public class Measurement {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(nullable = false)
    private String name;
	
	@Column(nullable = false, length = 5)
    private String symbol;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
	
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
	
    public String getSymbol() { return symbol; }
    public void setSymbol(String symbol) { this.symbol = symbol; }
}