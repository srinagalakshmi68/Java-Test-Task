package com.example.demo.model;

import lombok.Data;
//import javax.persistence.*;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@Entity
@Data
@Table
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDate dob;

    @ElementCollection
    private List<String> phones;

    @ElementCollection
    private List<String> emails;

    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    private BankAccount bankAccount;
}
