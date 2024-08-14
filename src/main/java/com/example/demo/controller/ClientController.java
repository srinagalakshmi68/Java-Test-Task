package com.example.demo.controller;

import com.example.demo.dto.ClientDTO;
import com.example.demo.dto.SearchCriteria;
import com.example.demo.dto.TransferRequestDTO;
import com.example.demo.model.Client;
import com.example.demo.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PostMapping("/create")
    public Client createClient(@RequestBody ClientDTO clientDTO) {
        return clientService.createClient(clientDTO);
    }

    @GetMapping("/search")
    public List<Client> searchClients(@RequestBody SearchCriteria criteria) {
        return clientService.searchClients(criteria);
    }

    @PostMapping("/{fromClientId}/transfer")
    public void transferMoney(@PathVariable Long fromClientId, @RequestBody TransferRequestDTO requestDTO) {
        clientService.transferMoney(fromClientId, requestDTO);
    }
}