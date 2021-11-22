package com.example.demo.service;

import com.example.demo.entity.CalendarEntity;
import com.example.demo.repository.CalendarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CalendarService {

    private final CalendarRepository calendarRepository;

    public void save(CalendarEntity calendar) {
        calendarRepository.save(calendar);
    }
}
