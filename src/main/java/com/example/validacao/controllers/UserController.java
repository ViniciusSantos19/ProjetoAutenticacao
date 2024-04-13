package com.example.validacao.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.validacao.dtos.CreateUserDto;
import com.example.validacao.dtos.LoginUserDto;
import com.example.validacao.dtos.RecoveryJwtTokenDto;
import com.example.validacao.services.UserService;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
	@Autowired
	UserService userService;
	
	@PostMapping("/login")
	public ResponseEntity<RecoveryJwtTokenDto> authentication(@RequestBody LoginUserDto loginUserDtot) {
		RecoveryJwtTokenDto recoveryJwtTokenDto = userService.AuthenticateUser(loginUserDtot);
		return  new ResponseEntity<>(recoveryJwtTokenDto, HttpStatus.OK);
	}
	
	 @PostMapping
	    public ResponseEntity<Void> createUser(@RequestBody CreateUserDto createUserDto) {
	        userService.createUser(createUserDto);
	        return new ResponseEntity<>(HttpStatus.CREATED);
	    }
}
