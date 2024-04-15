package com.example.validacao.dtos;

import java.util.Set;

public record RecoveryJwtTokenDto(
    String token,
    Set<String> roles,
    Long id) {

}
