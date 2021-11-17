package com.example.demo.DTO;

import com.example.demo.entity.BankEntity;
import com.example.demo.entity.ClientEntity;
import com.example.demo.entity.OfferEntity;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class ClientDTO {

    private String name;

    private String phone;

    private String email;

    private String passport;

    private BankEntity bank;

    private List<OfferEntity> offers;

    public static ClientDTO toDTO(ClientEntity entity){
        entity.getBank().setClients(null);
        List<OfferEntity> offers = entity.getOffers().stream()
                .peek(p->p.setClient(null))
                .collect(Collectors.toList());
        return new ClientDTO(
                entity.getName(),
                entity.getEmail(),
                entity.getPhone(),
                entity.getPassport(),
                entity.getBank(),
                offers
        );
    }
}
