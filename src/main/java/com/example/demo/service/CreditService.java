package com.example.demo.service;

import com.example.demo.entity.ClientEntity;
import com.example.demo.entity.CreditEntity;
import com.example.demo.exception.NoSuchCreditException;
import com.example.demo.repository.CreditRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreditService {
    private final CreditRepository creditRepository;

    public CreditEntity findById(String id){
        Optional<CreditEntity> entity = creditRepository.findById(id);
        if (entity.isPresent())
            return entity.get();
        else
            throw new NoSuchCreditException();
    }

    public List<CreditEntity> findAllCreditsById(String value) {
        List<CreditEntity> l = creditRepository.findAllByIdStartsWithIgnoreCase(value);
        return l;
    }

    public void deleteCredit(CreditEntity credit) {
        creditRepository.delete(credit);
    }

    public void save(CreditEntity credit) {
        creditRepository.save(credit);
    }
}
