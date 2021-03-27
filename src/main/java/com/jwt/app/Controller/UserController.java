package com.jwt.app.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jwt.app.Entity.AuthRequest;
import com.jwt.app.Utill.JWTutill;

@RestController
public class UserController {
	
	@Autowired
	private JWTutill jwtUtill;
	@Autowired
	private AuthenticationManager authManager;
	
	@GetMapping("/user")
	
	public String welcome() {
		return "Welocme!!!USER";
	}
	@GetMapping("/admin")
	
	public String admin() {
		return "Welocme!!!ADMIN";
	}
	@PostMapping("/authenticate")
	public String generateToken(@RequestBody AuthRequest request) throws Exception{
		
		String userName=request.getUserName();
		String password=request.getPassword();
		try {
		authManager.authenticate(new UsernamePasswordAuthenticationToken(userName,password));
		}
		catch(Exception e) {
			throw new Exception("invalid U/P");
		}
		
		
		return jwtUtill.generateToken(userName);
		
	}
	
}
