package com.example.validacao.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.example.validacao.config.securiry.WebSecurityConfig;
import com.example.validacao.dtos.CreateUserDto;
import com.example.validacao.dtos.LoginUserDto;
import com.example.validacao.dtos.RecoveryJwtTokenDto;
import com.example.validacao.entities.Role;
import com.example.validacao.entities.User;
import com.example.validacao.repositories.UserRepository;
import com.example.validacao.userDetails.UserDetailsImpl;

@Service
public class UserService {
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenService jwtTokenService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private WebSecurityConfig webSecurityConfig;
	
	public RecoveryJwtTokenDto AuthenticateUser(LoginUserDto loginUserDto) {
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =  new UsernamePasswordAuthenticationToken(loginUserDto.email(), loginUserDto.senha());
		
		Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken); 
		
		UserDetailsImpl userDetailsImpl = (UserDetailsImpl) authentication.getPrincipal();
		
		return new RecoveryJwtTokenDto(jwtTokenService.generateToken(userDetailsImpl));
	}
	
	public void createUser(CreateUserDto createUserDto) {
		User user = new User(createUserDto.email(),
				webSecurityConfig.passwordEncoder().encode(createUserDto.senha()),
				List.of(new Role(createUserDto.role())));
		
		userRepository.save(user);
	}
}
