package com.promineotech.jeep.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.promineotech.jeep.entity.Jeep;
import com.promineotech.jeep.service.JeepSalesService;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@Slf4j
public class BasicJeepSalesController implements JeepSalesController {

	private final Logger Logger = LoggerFactory.getLogger(BasicJeepSalesController.class);
	
	@Autowired
	private JeepSalesService jeepSalesService;
	
	@Override
	public List<Jeep> fetchJeeps(String model, String trim) {
		
		Logger.info("model={}, trim={}", model, trim);	
		return jeepSalesService.fetchJeeps(model, trim);
	}
	
	
	
	
	

}
