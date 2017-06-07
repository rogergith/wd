package com.wd.app.ligas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wd.app.ligas.model.Deporte;
import com.wd.app.ligas.model.Equipo;
import com.wd.app.ligas.service.LigasService;

@RestController
@RequestMapping("/ligas")
public class LigasController {

	@Autowired
	@Qualifier("LigaServiceImpl")
	private LigasService ligaService;
	
	/*
	 * http://localhost:9010/ligas/
	 */
	@PostMapping("/")
	public void almacenar(@RequestBody Deporte deporte){
	
		ligaService.almacenarDeporte(deporte);
		
	}
	
	
}
