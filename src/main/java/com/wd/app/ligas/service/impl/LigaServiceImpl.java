package com.wd.app.ligas.service.impl;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.wd.app.ligas.model.Deporte;
import com.wd.app.ligas.model.Equipo;
import com.wd.app.ligas.service.LigasService;

@Service("LigaServiceImpl")
public class LigaServiceImpl implements LigasService {

	private static String URL_POST_SAVE_TEAM = "http://192.168.1.48:8080/ligas/deporte/";
	
	@Override
	public void almacenarDeporte(Deporte deporte) {
		
		RestTemplate restTemplate = new RestTemplate();
		
		restTemplate.postForEntity(URL_POST_SAVE_TEAM, deporte, Deporte.class);
	
	}

	
	
}
