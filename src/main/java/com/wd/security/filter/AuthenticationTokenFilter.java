package com.wd.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.wd.security.component.JwtTokenUtil;

public class AuthenticationTokenFilter extends OncePerRequestFilter {

	private static final Log LOG = LogFactory.getLog(AuthenticationTokenFilter.class);
	
	@Autowired
	@Qualifier("AdministradorUserDetailService")
	private UserDetailsService userDetailsService; 
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
	
		String authToken = request.getHeader("Authorization");
		
		String username = jwtTokenUtil.getUsernameFromToken(authToken);
		
		LOG.info("bandera 0 - username: "+username);
		
		if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
		
			LOG.info("bandera 1");
			
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);
			
			LOG.info("bandera 2 "+userDetails.toString());
			
			if(jwtTokenUtil.validateToken(authToken, userDetails)){
			
				LOG.info("bandera 3 ");
				
				UsernamePasswordAuthenticationToken authentication = 
						new UsernamePasswordAuthenticationToken(userDetails, null ,userDetails.getAuthorities());
				
				
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				LOG.info("User '"+username+"' autenticado 'Configuracion del contexto de seguridad'");
			
				SecurityContextHolder.getContext().setAuthentication(authentication);
				
			}
			
		}
		
		chain.doFilter(request, response);
	
	}

}
