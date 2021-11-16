package com.example.demo.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "client")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    private String phone;

    private String email;

    private String passport;

    @ManyToOne(fetch = FetchType.EAGER)
    private BankEntity bank;
}
