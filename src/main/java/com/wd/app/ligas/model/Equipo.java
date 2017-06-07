package com.wd.app.ligas.model;

import java.util.List;

public class Equipo {

	private int id;
	
	private String equipo;
	
	private List<Torneo> torneos;
	
	public Equipo(){}

	public Equipo(int id, String equipo, List<Torneo> torneos) {
		super();
		this.id = id;
		this.equipo = equipo;
		this.torneos = torneos;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEquipo() {
		return equipo;
	}

	public void setEquipo(String equipo) {
		this.equipo = equipo;
	}

	public List<Torneo> getTorneos() {
		return torneos;
	}

	public void setTorneos(List<Torneo> torneos) {
		this.torneos = torneos;
	}
	
	

}