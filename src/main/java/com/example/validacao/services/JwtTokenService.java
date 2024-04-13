package com.example.validacao.services;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.validacao.userDetails.UserDetailsImpl;

@Service
public class JwtTokenService {
	private static final String SECRET_KEY = "";
	
	private static final String ISSUER = "";
	
	public String generateToken(UserDetailsImpl user) {
		Algorithm algoritmo = Algorithm.HMAC256(SECRET_KEY);
		return JWT.create()
					.withIssuer(ISSUER)
					.withIssuedAt(creationDate())
					.withExpiresAt(expirationDay())
					.withSubject(user.getUsername())
					.sign(algoritmo);
	}
	
	 public String getSubjectFromToken(String token) {
	        try {
	            // Define o algoritmo HMAC SHA256 para verificar a assinatura do token passando a chave secreta definida
	            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
	            return JWT.require(algorithm)
	                    .withIssuer(ISSUER) // Define o emissor do token
	                    .build()
	                    .verify(token) // Verifica a validade do token
	                    .getSubject(); // Obtém o assunto (neste caso, o nome de usuário) do token
	        } catch (JWTVerificationException exception){
	            throw new JWTVerificationException("Token inválido ou expirado.");
	        }
	    }

	private Instant creationDate() {
		return ZonedDateTime.now(ZoneId.of("America/Recife")).toInstant();
	}

	private Instant expirationDay() {
		 return ZonedDateTime.now(ZoneId.of("America/Recife")).plusHours(4).toInstant();
	}
}
