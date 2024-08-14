package com.example.demo.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class TransferRequestDTO {
    private Long toClientId;
    private BigDecimal amount;
}