package com.example.demo.service;

import com.example.demo.entity.BankEntity;
import com.example.demo.entity.ClientEntity;
import com.example.demo.repository.BankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BankService {

    private final BankRepository bankRepository;

    public List<BankEntity> findAllBanks() {
        List<BankEntity> l = (List<BankEntity>)bankRepository.findAll();
        return l;
    }

    public List<BankEntity> findAllBanksByName(String value) {
        List<BankEntity> l = (List<BankEntity>) bankRepository.findByNameStartsWithIgnoreCase(value);
        return l;
    }

    public void save(BankEntity bank) {
        bankRepository.save(bank);
    }

    public void delete(BankEntity bank) {
        bankRepository.delete(bank);
    }
}
