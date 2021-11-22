package com.example.demo.repository;

import com.example.demo.entity.BankEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends CrudRepository<BankEntity,String> {
    Iterable<BankEntity> findByNameStartsWithIgnoreCase(String value);
}
