package com.example.demo.Service;

import com.example.demo.dto.TransferRequestDTO;
import com.example.demo.model.BankAccount;
import com.example.demo.model.Client;
import com.example.demo.repository.BankAccountRepository;
import com.example.demo.repository.ClientRepository;
import com.example.demo.service.ClientService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ClientServiceTest {

    private ClientService clientService;
    private ClientRepository clientRepository;
    private BankAccountRepository bankAccountRepository;

    @BeforeEach
    void setUp() {
        clientRepository = mock(ClientRepository.class);
        bankAccountRepository = mock(BankAccountRepository.class);
        clientService = new ClientService(clientRepository, bankAccountRepository);
    }

    @Test
    void transferMoney_InsufficientFunds_ThrowsException() {
        Client fromClient = new Client();
        BankAccount fromAccount = new BankAccount();
        fromAccount.setCurrentBalance(BigDecimal.valueOf(50));
        fromClient.setBankAccount(fromAccount);

        Client toClient = new Client();
        BankAccount toAccount = new BankAccount();
        toAccount.setCurrentBalance(BigDecimal.valueOf(100));
        toClient.setBankAccount(toAccount);

        when(clientRepository.findById(1L)).thenReturn(Optional.of(fromClient));
        when(clientRepository.findById(2L)).thenReturn(Optional.of(toClient));

        TransferRequestDTO requestDTO = new TransferRequestDTO();
        requestDTO.setToClientId(2L);
        requestDTO.setAmount(BigDecimal.valueOf(100));

        assertThrows(IllegalArgumentException.class, () -> {
            clientService.transferMoney(1L, requestDTO);
        });
    }
}