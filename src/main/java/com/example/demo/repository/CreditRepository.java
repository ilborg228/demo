package com.example.demo.repository;

import com.example.demo.entity.CreditEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditRepository extends CrudRepository<CreditEntity,Long> {
}
