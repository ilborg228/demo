package com.example.demo.repository;

import com.example.demo.entity.BankEntity;
import org.springframework.data.repository.CrudRepository;

public interface BankRepository extends CrudRepository<BankEntity,Long> {
}
