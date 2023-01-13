package com.promineotech.jeep.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.promineotech.jeep.controller.BasicJeepSalesController;
import com.promineotech.jeep.dao.JeepSalesDao;
import com.promineotech.jeep.entity.Jeep;
import com.promineotech.jeep.entity.JeepModel;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultJeepSalesService implements JeepSalesService {
	
	private JeepSalesDao jeepSalesDao;
	
	private final Logger Logger = LoggerFactory.getLogger(DefaultJeepSalesService.class);
	private List<Jeep> jeeps;
	
	@Override
	public List<Jeep> fetchJeeps(JeepModel model, String trim) {

		Logger.info("Fetch jeeps was called with model={} and trim=[]", model, trim);
		
		jeeps = jeepSalesDao.fetchJeeps(model, trim);
		
		return jeeps;
	}
}
