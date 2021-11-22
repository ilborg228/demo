package com.example.demo;

import com.example.demo.repository.BankRepository;
import com.example.demo.service.CreditService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@RequiredArgsConstructor
class DemoApplicationTests {

	@Autowired
	private CreditService creditService;

	@Test
	void contextLoads() {
	}

	@Test
	void getFromDatabase(){
		System.out.println(creditService.findById("1").get());
	}
}
