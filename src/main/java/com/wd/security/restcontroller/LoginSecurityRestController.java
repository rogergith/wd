package com.wd.security.restcontroller;

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

import com.wd.security.jwt.JwtTokenUtil;
import com.wd.security.jwt.token.JwtAuthenticationResponse;
import com.wd.security.restcontroller.form.LoginForm;

@RestController
@RequestMapping("/security")
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
	 * http://localhost:3351/security/login
	 */
	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthenticationResponse> login(@RequestBody LoginForm login){
		
		//perform the security		
		
		LOG.info("bandera 0");
		
		UsernamePasswordAuthenticationToken authToken =
				new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword());
		Authentication authentication = authManager.authenticate(authToken);
	
		LOG.info("bandera 1");
		
		if(authentication.isAuthenticated()){
			LOG.info("USTED ESTÁ AUTENTICADO");
		}else{
			LOG.info("USTED NO ESTÁ AUTENTICADO");
		}
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		LOG.info("bandera 2");
		UserDetails userDetails = userDetailsService.loadUserByUsername(login.getUsername());

		LOG.info("bandera 3");
		
		
		String token = jwtTokenUtil.generateToken(userDetails);
		
		LOG.info(token);
		
		return new ResponseEntity<JwtAuthenticationResponse>(new JwtAuthenticationResponse(token),HttpStatus.OK);
		
	}
	/*
	 * http://localhost:3351/security/protegido
	 */
	@GetMapping("/protegido")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> protegido(){
		
		
		LOG.info("RECURSO PROTEGIDO");
		
		
		return new ResponseEntity<String>(HttpStatus.OK);
		
		
	}
	
	
	
	
}
