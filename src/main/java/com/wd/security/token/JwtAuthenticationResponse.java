package com.wd.security.token;

import java.io.Serializable;

public class JwtAuthenticationResponse implements Serializable {

	private static final long serialVersionUID = -3491451625066877382L;

	private final String tokek;	
	
	public JwtAuthenticationResponse(String tokek) {
		super();
		this.tokek = tokek;
	}

	public String getTokek() {
		return tokek;
	}

	
	
}
