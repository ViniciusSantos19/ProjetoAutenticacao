package com.example.validacao.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.validacao.entities.User;
import com.example.validacao.repositories.UserRepository;
import com.example.validacao.userDetails.UserDetailsImpl;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = repository.findByEmail(username).orElseThrow(()-> new RuntimeException("Usuário não encontrado"));
		return new UserDetailsImpl(user);
	}
	
	
	
}
