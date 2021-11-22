package com.example.demo.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "credit")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class CreditEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(columnDefinition = "CHAR(32)")
    private String id;

    private double percent;

    private double limit;

    @ManyToOne
    @LazyCollection(LazyCollectionOption.FALSE)
    private BankEntity bank;

    @ToString.Exclude
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.REMOVE,mappedBy = "credit")
    private List<OfferEntity> offers;
}
