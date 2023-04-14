package com.gavriel.employees.thirdparty;

import org.springframework.beans.factory.annotation.Value;
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
	private final String finalURL;
	
	public CurrencyConverterRESTAPI(@Value("${thirdpary.currencyconverter.url}")String URL, @Value("${thirdpary.currencyconverter.apikey}")String apiKey)
	{
		this.finalURL = URL + "&apiKey=" + apiKey;
		System.out.println(finalURL);
		restTemplate = new RestTemplate();
	}

	@Override
	public double USDToILS(double usd) throws Exception {
		ResponseEntity<String> response = restTemplate.getForEntity(finalURL , String.class);
		if(HttpStatus.OK != response.getStatusCode())
		{
			throw new Exception("Bad status code");
		}
		ObjectMapper mapper = new ObjectMapper();
		String JsonResponse = response.getBody();
		JsonNode root = mapper.readTree(JsonResponse);
		JsonNode resultsNode = root.get("results");
		JsonNode usdIlsNode = resultsNode.get("USD_ILS");
		JsonNode valNode = usdIlsNode.get("val");
		return valNode.asDouble();
		
	}
}
