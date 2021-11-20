package com.example.demo.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.sql.Date;
import java.util.Calendar;
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

    @JoinColumn
    @ManyToOne
    private ClientEntity client;

    @OneToOne
    private CreditEntity credit;

    @Column
    private long loanAmount;

    @OneToMany(cascade = {CascadeType.ALL},mappedBy = "offer")
    @LazyCollection(LazyCollectionOption.FALSE)
    @ToString.Exclude
    private List<CalendarEntity> calendar;
}
