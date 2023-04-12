package com.gavriel.employees.services;

import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@Profile("prod")
public class CurrencyConverterRESTAPI implements ICurrencyConverter {
	
	private RestTemplate restTemplate;
	private static final String URL = "IDONTKNOW.com";
	
	public CurrencyConverterRESTAPI()
	{
		restTemplate = new RestTemplate();
	}

	@Override
	public double USDToILS(double usd) throws Exception {
		ResponseEntity<String> response = restTemplate.getForEntity(URL + "/what", String.class);
		if(HttpStatus.OK != response.getStatusCode())
		{
			throw new Exception("Bad status code");
		}
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(response.getBody());
		JsonNode convertRate = root.path("convertRate");
		return convertRate.asDouble();
	}
	

}
