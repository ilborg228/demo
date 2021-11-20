package com.example.demo.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "calendar")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class CalendarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @JoinColumn
    @ManyToOne
    private OfferEntity offer;

    private Date date;

    private double paymentAmount;

    private double bodyAmount;

    private double percentAmount;
}
