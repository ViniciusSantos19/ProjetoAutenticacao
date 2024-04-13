package com.example.validacao.dtos;

import java.util.List;

import com.example.validacao.entities.Role;

public record RecoveryUserDto(
			Long id,
			String email,
			List<Role> roles
		) {

}
