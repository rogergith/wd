package com.wd.security.entity;

import static javax.persistence.CascadeType.ALL;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * 
 * ROLE_ADMIN
 * ROLE_AGENTE
 * ROLE_JUGADOR
 * ROLE_DEMO
 * 
 * @author android
 *
 */
@Entity
@Table(name="roles")
public class Roles {

	@Id
	@Column(name = "idroles")
	private int idroles;
	
	@Column(name = "rol")
	private String rol;
	
	@Column(name = "descripcion")
	private String descripcion;

	@ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
	private List<Administrador> administradores;
	
	@ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
	private List<Agente> agentes;
	
	public Roles(){}

	public int getIdroles() {
		return idroles;
	}

	public void setIdroles(int idroles) {
		this.idroles = idroles;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Administrador> getAdministradores() {
		return administradores;
	}

	public void setAdministradores(List<Administrador> administradores) {
		this.administradores = administradores;
	}

	public List<Agente> getAgentes() {
		return agentes;
	}

	public void setAgentes(List<Agente> agentes) {
		this.agentes = agentes;
	}
	
	

}
