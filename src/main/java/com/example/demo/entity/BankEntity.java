package com.example.demo.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "bank")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class BankEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToMany(mappedBy = "bank",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<CreditEntity> credits;

    @OneToMany(mappedBy = "bank",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<ClientEntity> clients;
}
