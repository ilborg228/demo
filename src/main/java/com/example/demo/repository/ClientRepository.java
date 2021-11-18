package com.example.demo.repository;

import com.example.demo.entity.ClientEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ClientRepository extends CrudRepository<ClientEntity,Long> {
    Iterable<ClientEntity> findByNameStartsWithIgnoreCase(String filterText);
}
