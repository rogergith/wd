package com.wd.security.jwt.dtoentity;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class JwtAdministrador implements UserDetails {

	private static final long serialVersionUID = 334850282724465168L;

	private String username;
	private String password;
	private Collection<? extends GrantedAuthority> roles;
	
	
	public JwtAdministrador(){
		
	}
	
	public JwtAdministrador(String username, String password, Collection<? extends GrantedAuthority> roles) {
		super();
		this.username = username;
		this.password = password;
		this.roles = roles;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return roles;
	}

	@Override
	public String getPassword() {
		
		return password;
	}

	@Override
	public String getUsername() {
		
		return username;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isEnabled() {
		
		return true;
	}

}
