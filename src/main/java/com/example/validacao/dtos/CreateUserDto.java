package com.example.validacao.dtos;

import com.example.validacao.entities.RoleName;

public record CreateUserDto(
			String email,
			String senha,
			RoleName role
		) {

}
