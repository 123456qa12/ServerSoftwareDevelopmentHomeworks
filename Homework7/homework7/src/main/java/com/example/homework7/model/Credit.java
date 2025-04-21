package com.example.homework7.model;

import java.time.LocalDateTime;

public class Credit extends Payment {

    private String number;
    private String type;
    private LocalDateTime expDate;

    public String getNumber() { return number; }
    public void setNumber(String number) { this.number = number; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public LocalDateTime getExpDate() { return expDate; }
    public void setExpDate(LocalDateTime expDate) { this.expDate = expDate; }
}