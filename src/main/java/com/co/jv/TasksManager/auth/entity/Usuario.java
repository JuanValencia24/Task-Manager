package com.co.jv.TasksManager.auth.entity;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Document(collection = "usuarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario implements UserDetails {
		@Id
		private String id;
		@NotNull
		private String primerNombre;
		private String segundoNombre;
		@NotNull
		private String primerApellido;
		private String segundoApellido;
		@NotNull
		private String codigoPais;
		@NotNull
		@Indexed(unique = true)
		private String numeroTelefono;
		@NotNull
		private String clave;
		private ERol rol;

		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			return List.of(new SimpleGrantedAuthority(rol.name()));
		}

		@Override
		public String getPassword() {
			return clave;
		}

		@Override
		public String getUsername() {
			return numeroTelefono;
		}

		@Override
		public boolean isAccountNonExpired() {
			return true;
		}

		@Override
		public boolean isAccountNonLocked() {
			return true;
		}

		@Override
		public boolean isCredentialsNonExpired() {
			return true;
		}

		@Override
		public boolean isEnabled() {
			return true;
		}
}
