package com.example.demo.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "credit")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class CreditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private double percent;

    private double limit;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "credits", cascade = CascadeType.ALL)
    private List<BankEntity> bank;

    @JoinColumn
    @OneToOne(cascade = {CascadeType.ALL})
    private OfferEntity offer;
}
