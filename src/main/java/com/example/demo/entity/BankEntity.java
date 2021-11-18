package com.example.demo.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

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

    @JoinTable(name = "credit_bank",
            joinColumns = @JoinColumn(name = "bank_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "credit_entity_id", referencedColumnName = "bank_id"))
    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(cascade = {CascadeType.ALL})
    @ToString.Exclude
    private List<CreditEntity> credits;

    @JoinTable(name = "bank_clients",
            joinColumns = @JoinColumn(name = "bank_entity_id", referencedColumnName = "clients_id"))
    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(cascade = {CascadeType.ALL})
    @ToString.Exclude
    private List<ClientEntity> clients;
}
