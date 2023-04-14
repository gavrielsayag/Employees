package com.gavriel.employees;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.gavriel.employees.thirdparty.ICurrencyConverter;

@SpringBootTest
@ActiveProfiles("prod")
class TestCurrencyConverter {
	
	@Autowired
	ICurrencyConverter converter;

	@Test
	void test() throws Exception 
	{
		System.out.println(converter.USDToILS(1));
	}

}
