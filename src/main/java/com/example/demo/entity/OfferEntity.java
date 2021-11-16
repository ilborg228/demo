package com.example.demo.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "offer")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class OfferEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private ClientEntity client;

    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private CreditEntity credit;

    @Column
    private long loanAmount;

//    private class PaymentCalendar{
//
//    }
}
