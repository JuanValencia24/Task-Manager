package com.co.jv.TasksManager.auth.payload.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterRequest {
	private String primerNombre;
	private String segundoNombre;
	private String primerApellido;
	private String segundoApellido;
	private String codigoPais;
	private String numeroTelefono;
	private String clave;
}
