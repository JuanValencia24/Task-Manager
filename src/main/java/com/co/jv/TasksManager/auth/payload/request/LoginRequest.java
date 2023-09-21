package com.co.jv.TasksManager.auth.payload.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginRequest {
	private String phoneNumber;
	private String clave;
}
