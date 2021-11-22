package com.example.demo.repository;

import com.example.demo.entity.CalendarEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalendarRepository extends CrudRepository<CalendarEntity,String> {
}
