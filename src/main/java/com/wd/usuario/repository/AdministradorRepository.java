package com.wd.usuario.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wd.usuario.entity.Administrador;

@Repository("AdministradorRepository")
public interface AdministradorRepository extends JpaRepository<Administrador, Serializable> {

	public Administrador findByAdministrador(String username);
	
}
