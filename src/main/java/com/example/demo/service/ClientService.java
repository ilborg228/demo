package com.example.demo.service;

import com.example.demo.entity.BankEntity;
import com.example.demo.entity.ClientEntity;
import com.example.demo.repository.BankRepository;
import com.example.demo.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;


    public List<ClientEntity> findAllClientsByName(String value) {
        List<ClientEntity> l = (List<ClientEntity>) clientRepository.findByNameStartsWithIgnoreCase(value);
        return l;
    }

    public Optional<ClientEntity> findById(String id){
        Optional<ClientEntity> op = clientRepository.findById(id);
        return op;
    }

    public void saveClient(ClientEntity client) {
        clientRepository.save(client);
    }

    public void deleteClient(ClientEntity client) {
        clientRepository.delete(client);
    }
}
