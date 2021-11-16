package com.example.demo.repository;

import com.example.demo.entity.ClientEntity;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<ClientEntity,Long> {
}
