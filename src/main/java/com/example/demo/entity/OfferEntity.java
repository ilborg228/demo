package com.example.demo.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "offer")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class OfferEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(columnDefinition = "CHAR(32)")
    private String id;

    @JoinColumn
    @ManyToOne
    private ClientEntity client;

    @ManyToOne
    @LazyCollection(LazyCollectionOption.FALSE)
    private CreditEntity credit;

    @Column
    private double loanAmount;

    @OneToMany(cascade = {CascadeType.ALL},mappedBy = "offer")
    @LazyCollection(LazyCollectionOption.FALSE)
    @ToString.Exclude
    private List<CalendarEntity> calendar;
}
