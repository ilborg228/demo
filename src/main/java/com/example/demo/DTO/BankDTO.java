package com.example.demo.DTO;

import com.example.demo.entity.BankEntity;
import com.example.demo.entity.ClientEntity;
import com.example.demo.entity.CreditEntity;
import com.example.demo.entity.OfferEntity;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class BankDTO {

    private List<CreditEntity> credits;

    private List<ClientEntity> clients;

    public static BankDTO toDTO(BankEntity entity){
        List<CreditEntity> credits = entity.getCredits().stream()
                .peek(p->p.setBank(null))
                .collect(Collectors.toList());

        List<ClientEntity> clients = entity.getClients().stream()
                .peek(p->p.setBank(null))
                .collect(Collectors.toList());

        return new BankDTO(credits,clients);
    }
}
