package com.wd.usuario.entity;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "administrador")
public class Administrador {

	@Id
	@Column(name = "idadministradores")
	private int cod_administrador;

	@Column(name = "administrador")
	private String administrador;
	
	@Column(name = "password")
	private String password;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
            name = "administrador_roles",
            joinColumns = {
            	@JoinColumn(name = "id_admin", referencedColumnName = "idadministradores")
            },
            inverseJoinColumns = {
            	@JoinColumn(name = "id_rol", referencedColumnName = "idroles")
            }
    )
	private List<Roles> roles;
		
	@OneToMany
	@JoinColumn(name = "id_creador")
	private List<Administrador> usuarios;
	
	public Administrador(){}

	public int getCod_administrador() {
		return cod_administrador;
	}

	public void setCod_administrador(int cod_administrador) {
		this.cod_administrador = cod_administrador;
	}

	public String getAdministrador() {
		return administrador;
	}

	public void setAdministrador(String administrador) {
		this.administrador = administrador;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Roles> getRoles() {
		return roles;
	}

	public void setRoles(List<Roles> roles) {
		this.roles = roles;
	}

	public List<Administrador> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Administrador> usuarios) {
		this.usuarios = usuarios;
	}

		
	
}
