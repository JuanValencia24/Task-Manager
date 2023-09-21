package com.co.jv.TasksManager.auth;

import com.co.jv.TasksManager.auth.payload.request.LoginRequest;
import com.co.jv.TasksManager.auth.payload.request.RegisterRequest;
import com.co.jv.TasksManager.auth.service.AuthServicio;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/")
@RequiredArgsConstructor
public class AuthControlador {

	private final AuthServicio authServicio;

	@PostMapping("/sign-up")
	public ResponseEntity crearUsuario (
			@RequestBody RegisterRequest request
	) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("authorization", authServicio.crearUsuario(request));

		return new ResponseEntity("", httpHeaders, HttpStatusCode.valueOf(HttpStatus.SC_CREATED));
	}

	@PostMapping("/sign-in")
	public ResponseEntity iniciarSesion (
			@RequestBody LoginRequest request
	) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("authorization", authServicio.iniciarSesion(request));

		return new ResponseEntity("", httpHeaders, HttpStatusCode.valueOf(HttpStatus.SC_OK));
	}
}
