package com.example.demo.service;

import com.example.demo.entity.BankEntity;
import com.example.demo.entity.ClientEntity;
import com.example.demo.repository.BankRepository;
import com.example.demo.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final BankRepository bankRepository;


    public List<BankEntity> findAllBanks() {
        List<BankEntity> l = (List<BankEntity>)bankRepository.findAll();
        return l;
    }

    public List<ClientEntity> findAllClientsByName(String value) {
        List<ClientEntity> l = (List<ClientEntity>) clientRepository.findByNameStartsWithIgnoreCase(value);
        return l;
    }

    public void saveClient(ClientEntity client) {
        clientRepository.save(client);
    }

    public void deleteClient(ClientEntity client) {
        clientRepository.delete(client);
    }
}
