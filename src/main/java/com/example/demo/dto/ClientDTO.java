package com.example.demo.dto;


import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class ClientDTO {

    private String name;
    private LocalDate dob;
    private List<String> phones;
    private List<String> emails;
    private String password;
    private BigDecimal initialBalance;
}