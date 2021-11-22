package com.example.demo.repository;

import com.example.demo.entity.CreditEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CreditRepository extends CrudRepository<CreditEntity,String> {
    Optional<CreditEntity> findById(String id);
}
