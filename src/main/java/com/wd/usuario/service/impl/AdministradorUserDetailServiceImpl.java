package com.wd.usuario.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.wd.usuario.entity.Administrador;
import com.wd.usuario.entity.Roles;
import com.wd.usuario.repository.AdministradorRepository;

@Service("AdministradorUserDetailService")
public class AdministradorUserDetailServiceImpl implements UserDetailsService {

	@Autowired
	@Qualifier("AdministradorRepository")
	private AdministradorRepository administradorRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			
		Administrador administrador = administradorRepository.findByAdministrador(username);
		
		List<GrantedAuthority> authorities = buildAuthorities(administrador.getRoles());
		
		return buildUser(administrador, authorities);
		
		
	}

	private User buildUser(Administrador administrador, List<GrantedAuthority> authorities){
		
		return new User(administrador.getAdministrador(), administrador.getPassword(), true, true, true, true, authorities);
		
	}
	
	private List<GrantedAuthority> buildAuthorities(List<Roles> userRoles){
		Set<GrantedAuthority> auths = new HashSet<GrantedAuthority>();
		for(Roles userRole :userRoles){
			auths.add(new SimpleGrantedAuthority(userRole.getRol()));	
		}	
		return new ArrayList<GrantedAuthority>(auths);
	}
	
	
}
