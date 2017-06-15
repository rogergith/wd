package com.wd.app.ligas.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.wd.app.ligas.model.Deporte;
import com.wd.app.ligas.model.dto.DtoDeportes;
import com.wd.app.ligas.service.DeporteService;

@Service("DeporteServiceImpl")
public class DeporteServiceImpl implements DeporteService {

	private static final Log LOG = LogFactory.getLog(DeporteServiceImpl.class);
	//http://192.168.1.48:8080/ligas/api
	
	//private static String URL_BASE = "http://192.168.1.48:8080";

	private static String URL_BASE = "http://168.181.186.37:8080";
	
	private static String URL_POST_SAVE_TEAM = URL_BASE+"/ligas/api/deporte";
	private static String URL_GET_TEAMS = URL_BASE+"/ligas/api/deportes";
	
	//private static String URL_POST_SAVE_TEAM = "http://168.181.186.37:8080/ligas/api/ligas/deporte";
	//private static String URL_GET_TEAMS = "http://168.181.186.37:8080/ligas/api/ligas/deportes";
	
	
	@Override
	public void almacenarDeporte(Deporte deporte) {
		
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<Deporte> entity = restTemplate.postForEntity(URL_POST_SAVE_TEAM, deporte, Deporte.class);
		
		LOG.info(entity.getStatusCode());
		LOG.info(entity.getStatusCodeValue());
		LOG.info(entity.getBody());
		LOG.info(entity.getHeaders());
		
	}

	@Override
	public DtoDeportes deportes(){
		
		RestTemplate restTemplate = new RestTemplate();
		
		DtoDeportes deportes = restTemplate.getForObject(URL_GET_TEAMS, DtoDeportes.class);
		
		
		return deportes;
		
	}

	
	
}
