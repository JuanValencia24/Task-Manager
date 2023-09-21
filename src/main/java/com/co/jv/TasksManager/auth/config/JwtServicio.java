package com.co.jv.TasksManager.auth.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtServicio {

	private static final String KEY = "fe541785ecd48356bfe18f20d46cfbc9cbf440c6b5799301a4cf4aee383a7f9e";

	public String extraerNumeroTelefono(String token) {
		return claimsJwt(token, Claims::getSubject);
	}

	public <T> T claimsJwt(String token, Function<Claims, T> extractor) {
		final Claims claims = todosClaimsJwt(token);
		return extractor.apply(claims);
	}

	public String generarToken(UserDetails userDetails) {
		return generarToken(new HashMap<>(), userDetails);
	}

	public String generarToken(Map<String, Object> otrosClaims, UserDetails userDetails) {
		return Jwts
				.builder()
				.setClaims(otrosClaims)
				.setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date((System.currentTimeMillis() + 1000 * 60 * 24 * 7)))
				.signWith(getClaveSecreta(), SignatureAlgorithm.HS256)
				.compact();
	}

	public boolean tokenValido(String token, UserDetails userDetails) {
		final String username = extraerNumeroTelefono(token);
		return (username.equals(userDetails.getUsername())) && !tokenExpirado(token);
	}

	private boolean tokenExpirado(String token) {
		return fechaExpiracion(token).before(new Date());
	}

	private Date fechaExpiracion(String token) {
		return claimsJwt(token, Claims::getExpiration);
	}

	private Claims todosClaimsJwt(String token) {
		return Jwts
				.parserBuilder()
				.setSigningKey(getClaveSecreta())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}

	private Key getClaveSecreta() {
		byte[] keyBytes = Decoders.BASE64.decode(KEY);
		return Keys.hmacShaKeyFor(keyBytes);
	}
}
