package com.wd.security.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity 
@Table(name = "agente")
public class Agente {

	@Id
	@Column(name = "idagente")
	private int cod_agente;
	
	@Column(name = "agente")
	private String agente;
	
	@Column(name = "password")
	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
		name = "agente_roles",
		joinColumns = {
			@JoinColumn(name = "id_agente", referencedColumnName = "idagente")
		},
		inverseJoinColumns = {
			@JoinColumn(name = "id_rol", referencedColumnName = "idroles")	
		}
	)
	private List<Roles> roles;

	public int getCod_agente() {
		return cod_agente;
	}

	public void setCod_agente(int cod_agente) {
		this.cod_agente = cod_agente;
	}

	public String getAgente() {
		return agente;
	}

	public void setAgente(String agente) {
		this.agente = agente;
	}

	public List<Roles> getRoles() {
		return roles;
	}

	public void setRoles(List<Roles> roles) {
		this.roles = roles;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
