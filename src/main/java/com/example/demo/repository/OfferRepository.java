package com.example.demo.repository;

import com.example.demo.entity.OfferEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository extends CrudRepository<OfferEntity,Long> {
}
