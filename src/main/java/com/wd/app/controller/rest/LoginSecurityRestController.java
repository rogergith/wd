package com.wd.app.controller.rest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wd.security.component.JwtTokenUtil;
import com.wd.security.token.JwtAuthenticationResponse;
import com.wd.usuario.entity.dto.JwtUser;

@RestController
@RequestMapping("/api")
public class LoginSecurityRestController {

	private static final Log LOG = LogFactory.getLog(LoginSecurityRestController.class);
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	@Qualifier("AdministradorUserDetailService")
	private UserDetailsService userDetailsService;
	
	@Autowired
	@Qualifier("JwtTokenUtil")
	private JwtTokenUtil jwtTokenUtil;
	
	/*
	 * http://localhost:9010/security/login
	 */
	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthenticationResponse> login(@RequestBody LoginForm login){
		
		//perform the security		
		LOG.info("bandera 0 "+login.toString());
		
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword());
		
		LOG.info("bandera 1 "+login.toString());
			
		
		Authentication authentication = authManager.authenticate(authToken);
	
		
		LOG.info("bandera 2");
		
		if(authentication.isAuthenticated()){
			LOG.info("USTED ESTÁ AUTENTICADO");
		}else{
			LOG.info("USTED NO ESTÁ AUTENTICADO");
		}
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		LOG.info("bandera 3");
		UserDetails userDetails = userDetailsService.loadUserByUsername(login.getUsername());
		
		LOG.info("bandera 4");
		
		String token = jwtTokenUtil.generateToken(userDetails);
		
		LOG.info(token);
		
		return new ResponseEntity<JwtAuthenticationResponse>(new JwtAuthenticationResponse(token),HttpStatus.OK);
		
	}
	/*
	 * http://localhost:9010/wd/security/protegido
	 */
	@GetMapping("/protegido")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> protegido(){
		
		
		LOG.info("RECURSO PROTEGIDO");
		
		
		return new ResponseEntity<String>(HttpStatus.OK);
		
		
	}
	
	
	
	
}
