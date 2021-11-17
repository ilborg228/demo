package com.example.demo.DTO;

import com.example.demo.entity.ClientEntity;
import com.example.demo.entity.CreditEntity;
import com.example.demo.entity.OfferEntity;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OfferDTO {

    private ClientEntity client;

    private CreditEntity credit;

    private long loanAmount;

    public static OfferDTO toDTO(OfferEntity entity){
        entity.getClient().setOffers(null);
        return new OfferDTO(
                entity.getClient(),
                entity.getCredit(),
                entity.getLoanAmount()
        );
    }
}
