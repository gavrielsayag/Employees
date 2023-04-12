package com.gavriel.employees.services;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({"dev", "default"})
public class MockCurrencyConverter implements ICurrencyConverter {

	@Override
	public double USDToILS(double usd) {
		return usd * 3.2;
	}

}
