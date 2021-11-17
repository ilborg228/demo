package com.example.demo.DTO;

import com.example.demo.entity.BankEntity;
import com.example.demo.entity.ClientEntity;
import com.example.demo.entity.CreditEntity;
import com.example.demo.entity.OfferEntity;
import lombok.AllArgsConstructor;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class CreditDTO {
    private double percent;

    private double limit;

    private BankEntity bank;

    public static CreditDTO toDTO(CreditEntity entity){
        entity.getBank().setClients(null);
        return new CreditDTO(
                entity.getPercent(),
                entity.getLimit(),
                entity.getBank()
        );
    }
}
