package com.wd.app.ligas.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wd.app.ligas.model.Deporte;
import com.wd.app.ligas.model.dto.DtoDeportes;
import com.wd.app.ligas.service.DeporteService;

@RestController
@RequestMapping("/api")
public class DeporteController {
	
	private static final Log LOG = LogFactory.getLog(DeporteController.class);
	
	@Autowired
	@Qualifier("DeporteServiceImpl")
	private DeporteService ds;
	
	/*
	 * http://localhost:9010/wd/deporte
	 */
	@PostMapping("/deporte")
	public void almacenar(@RequestBody Deporte deporte){
		
		LOG.info(deporte);
		
		ds.almacenarDeporte(deporte);
		
	}
	
	/*
	 * http://localhost:9010/wd/deportes
	 */
	@GetMapping("/deportes")
	public ResponseEntity<DtoDeportes> deportes(){
	
	
		return new ResponseEntity<DtoDeportes>(ds.deportes(),HttpStatus.OK);

	}
	
}
