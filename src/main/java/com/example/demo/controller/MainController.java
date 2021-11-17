package com.example.demo.controller;

import com.example.demo.entity.CreditEntity;
import com.example.demo.repository.CreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {


    @GetMapping("/")
    public ResponseEntity<?> mainPage(){
        return ResponseEntity.ok("!!");
    }
}
