package com.wd.app.controller.rest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recurso")
public class RestTestController {

	private static final Log LOG = LogFactory.getLog(RestTestController.class);
	
	/**
	 * http://localhost:3351/recurso/saludo
	 */
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/saludo")
	public void servicio0(){
		
		LOG.info("servicio ADMIN");
		
	}
	
	/**
	 * http://localhost:3351/recurso/saludo_agente
	 */
	
	@PreAuthorize("hasRole('AGENTE')")
	@GetMapping("/saludo_agente")
	public void servicio1(){
		
		LOG.info("servicio AGENTE");
		
	}
	
}
