package com.example.demo.service;

import com.example.demo.dto.ClientDTO;
import com.example.demo.dto.SearchCriteria;
import com.example.demo.dto.TransferRequestDTO;
import com.example.demo.exception.ClientNotFoundException;
import com.example.demo.model.BankAccount;
import com.example.demo.model.Client;
import com.example.demo.repository.BankAccountRepository;
import com.example.demo.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final BankAccountRepository bankAccountRepository;

    @Transactional
    public Client createClient(ClientDTO clientDTO) {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setInitialBalance(clientDTO.getInitialBalance());
        bankAccount.setCurrentBalance(clientDTO.getInitialBalance());

        Client client = new Client();
        client.setName(clientDTO.getName());
        client.setDob(clientDTO.getDob());
        client.setPhones(clientDTO.getPhones());
        client.setEmails(clientDTO.getEmails());
        client.setPassword(clientDTO.getPassword());
        client.setBankAccount(bankAccount);

        return clientRepository.save(client);
    }

    @Transactional
    public void transferMoney(Long fromClientId, TransferRequestDTO requestDTO) {
        Client fromClient = clientRepository.findById(fromClientId)
                .orElseThrow(() -> new ClientNotFoundException("Client not found"));

        Client toClient = clientRepository.findById(requestDTO.getToClientId())
                .orElseThrow(() -> new ClientNotFoundException("Recipient client not found"));

        BigDecimal fromBalance = fromClient.getBankAccount().getCurrentBalance();
        BigDecimal toBalance = toClient.getBankAccount().getCurrentBalance();
        BigDecimal amount = requestDTO.getAmount();

        if (fromBalance.compareTo(amount) < 0) {
            throw new IllegalArgumentException("Insufficient funds");
        }

        fromClient.getBankAccount().setCurrentBalance(fromBalance.subtract(amount));
        toClient.getBankAccount().setCurrentBalance(toBalance.add(amount));

        bankAccountRepository.save(fromClient.getBankAccount());
        bankAccountRepository.save(toClient.getBankAccount());
    }

    @Transactional(readOnly = true)
    public List<Client> searchClients(SearchCriteria criteria) {
        List<Client> clients = clientRepository.findAll();

        if (criteria.getName() != null && !criteria.getName().isEmpty()) {
            clients = clients.stream()
                    .filter(client -> client.getName().startsWith(criteria.getName()))
                    .collect(Collectors.toList());
        }

        if (criteria.getDob() != null) {
            clients = clients.stream()
                    .filter(client -> client.getDob().isAfter(criteria.getDob()) || client.getDob().isEqual(criteria.getDob()))
                    .collect(Collectors.toList());
        }

        if (criteria.getPhone() != null && !criteria.getPhone().isEmpty()) {
            clients = clients.stream()
                    .filter(client -> client.getPhones().contains(criteria.getPhone()))
                    .collect(Collectors.toList());
        }

        if (criteria.getEmail() != null && !criteria.getEmail().isEmpty()) {
            clients = clients.stream()
                    .filter(client -> client.getEmails().contains(criteria.getEmail()))
                    .collect(Collectors.toList());
        }

        return clients;
    }
}