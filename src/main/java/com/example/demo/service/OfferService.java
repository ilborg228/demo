package com.example.demo.service;

import com.example.demo.entity.OfferEntity;
import com.example.demo.repository.OfferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OfferService {
    private final OfferRepository offerRepository;

    public void save(OfferEntity o){
        offerRepository.save(o);
    }
}
