package com.example.demo.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class SearchCriteria {
    private String name;
    private LocalDate dob;
    private String phone;
    private String email;
}