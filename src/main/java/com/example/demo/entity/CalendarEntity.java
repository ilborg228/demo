package com.example.demo.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;
import java.util.UUID;

@Entity
@Table(name = "calendar")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class CalendarEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(columnDefinition = "CHAR(32)")
    private String id;

    @JoinColumn
    @ManyToOne
    private OfferEntity offer;

    private Date date;

    private double paymentAmount;

    private double bodyAmount;

    private double percentAmount;
}
