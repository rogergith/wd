package com.wd.app.ligas.model;

public class Deporte {

	private int id;
	
	private String deporte;
		
	public Deporte(){}

	public Deporte(int id, String deporte) {
		super();
		this.id = id;
		this.deporte = deporte;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDeporte() {
		return deporte;
	}

	public void setDeporte(String deporte) {
		this.deporte = deporte;
	}

	@Override
	public String toString() {
		return "Deporte [id=" + id + ", deporte=" + deporte + "]";
	}

	
		
}
