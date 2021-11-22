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
		CreditEntity entity = creditRepository.findById("ff8081817d47b233017d47b23a010000").get();
		creditRepository.delete(entity);
	}
}
