package com.example.demo;

import com.example.demo.entity.CreditEntity;
import com.example.demo.repository.CreditRepository;
import com.example.demo.service.CreditService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@RequiredArgsConstructor
class DemoApplicationTests {

	@Autowired
	private CreditRepository creditRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void getFromDatabase(){
		creditRepository.findAll();
	}
}
