package com.wd.app.ligas.service;

import java.util.List;

import com.wd.app.ligas.model.Deporte;
import com.wd.app.ligas.model.dto.DtoDeportes;

public interface DeporteService {

	public void almacenarDeporte(Deporte deporte);
	
	public DtoDeportes deportes();
}
