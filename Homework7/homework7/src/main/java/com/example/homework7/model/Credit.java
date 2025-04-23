package com.example.homework7.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "credit")
@PrimaryKeyJoinColumn(name = "payment_id")
public class Credit extends Payment {
	
	@Column(nullable = false, length = 16)
    private String number;
	
	@Column(nullable = false)
    private String type;
	
	@Column(name = "exp_date", nullable = false)
    private LocalDateTime expDate;

    public String getNumber() { return number; }
    public void setNumber(String number) { this.number = number; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public LocalDateTime getExpDate() { return expDate; }
    public void setExpDate(LocalDateTime expDate) { this.expDate = expDate; }
}