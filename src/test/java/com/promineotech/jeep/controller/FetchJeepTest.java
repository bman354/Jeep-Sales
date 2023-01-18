package com.promineotech.jeep.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import com.promineotech.jeep.controller.support.BaseTest;
import com.promineotech.jeep.entity.Jeep;
import com.promineotech.jeep.entity.JeepModel;

import lombok.Getter;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Sql(scripts = { "classpath:flyway/migrations/V1.0__Jeep_Schema.sql",
		"classpath:flyway/migrations/V1.1__Jeep_Data.sql" }, config = @SqlConfig(encoding = "utf-8"))


class FetchJeepTest extends BaseTest {

	@Autowired
	@Getter
	private TestRestTemplate restTemplate; 
	
	
	@Test
	void testThatJeepsAreReturnedWhenAValidModelAndTrimAreSupplied() {

		JeepModel model = JeepModel.WRANGLER;
		String trim = "Sport";
		String uri = String.format("%s?model=%s&trim=%s", getBaseUri(), model, trim);
		System.out.println(uri);
		
		
		// create a connection and get a response
		ResponseEntity<List<Jeep>> response = restTemplate.exchange(uri, HttpMethod.GET, null,
				new ParameterizedTypeReference<>() {
				});

		// make sure the response is 200
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		
		
		//get our actual jeep from our response and create an expected jeep
		List<Jeep> actual = response.getBody();
		List<Jeep> expected = buildExpected();
		
		actual.forEach(jeep -> jeep.setModelPK(null));
		
		assertThat(actual).isEqualTo(expected);
	}

	public List<Jeep> buildExpected() {
		List<Jeep> list = new LinkedList<Jeep>();

	    // @formatter:off
	    list.add(Jeep.builder()
	        .modelId(JeepModel.WRANGLER)
	        .trimLevel("Sport")
	        .numDoors(2)
	        .wheelSize(17)
	        .basePrice(new BigDecimal("28475.00"))
	        .build());
	    
	    
	    list.add(Jeep.builder()
	        .modelId(JeepModel.WRANGLER)
	        .trimLevel("Sport")
	        .numDoors(4)
	        .wheelSize(17)
	        .basePrice(new BigDecimal("31975.00"))
	        .build());
	    // @formatter:on
		return list;
	}

}
