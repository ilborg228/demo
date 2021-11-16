package com.example.demo.repository;

import com.example.demo.entity.CreditEntity;
import org.springframework.data.repository.CrudRepository;

public interface CreditRepository extends CrudRepository<CreditEntity,Long> {
}
