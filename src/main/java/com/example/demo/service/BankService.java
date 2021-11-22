package com.example.demo.service;

import com.example.demo.entity.BankEntity;
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
}
