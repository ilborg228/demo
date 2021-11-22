package com.example.demo.service;

import com.example.demo.entity.CreditEntity;
import com.example.demo.repository.CreditRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreditService {
    private final CreditRepository creditRepository;

    public Optional<CreditEntity> findById(String id){
        return creditRepository.findById(id);
    }
}
