package com.example.demo.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "client")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class ClientEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    private String phone;

    @Email
    private String email;

    private String passport;

    @ManyToMany(mappedBy = "clients")
    @LazyCollection(LazyCollectionOption.FALSE)
    @ToString.Exclude
    private List<BankEntity> bank;

    @JoinColumn
    @ToString.Exclude
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<OfferEntity> offers;
}
