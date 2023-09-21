package com.co.jv.TasksManager.auth.service;

import com.co.jv.TasksManager.auth.config.JwtServicio;
import com.co.jv.TasksManager.auth.entity.ERol;
import com.co.jv.TasksManager.auth.entity.Usuario;
import com.co.jv.TasksManager.auth.payload.request.LoginRequest;
import com.co.jv.TasksManager.auth.payload.request.RegisterRequest;
import com.co.jv.TasksManager.auth.repository.UsuarioRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServicio {

	private final UsuarioRepo usuarioRepo;
	private final PasswordEncoder passwordEncoder;
	private final JwtServicio jwtService;
	private final AuthenticationManager authenticationManager;
	public String crearUsuario(RegisterRequest request) {
		var usuario = new Usuario();
		usuario.setPrimerNombre(request.getPrimerNombre());
		usuario.setSegundoNombre(request.getSegundoNombre());
		usuario.setPrimerApellido(request.getPrimerApellido());
		usuario.setSegundoApellido(request.getSegundoApellido());
		usuario.setNumeroTelefono(request.getNumeroTelefono());
		usuario.setClave(passwordEncoder.encode(request.getClave()));
		usuario.setRol(ERol.USER);

		Optional<Usuario> existeUsuario = usuarioRepo.findUserByNumeroTelefono(request.getNumeroTelefono());
		if (!existeUsuario.isPresent()) {
			usuarioRepo.save(usuario);
		} else {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, "El número de telefono ya se encuentra registrado"
			);
		}


		return jwtService.generarToken(usuario);
	}

	public String iniciarSesion(LoginRequest request) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						request.getPhoneNumber(),
						request.getClave()
				)
		);

		var usuario = usuarioRepo.findUserByNumeroTelefono(request.getPhoneNumber())
				.orElseThrow();

		return jwtService.generarToken(usuario);
	}
}
