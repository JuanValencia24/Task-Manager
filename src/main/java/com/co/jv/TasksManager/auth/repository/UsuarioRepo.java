package com.co.jv.TasksManager.auth.repository;

import com.co.jv.TasksManager.auth.entity.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepo extends MongoRepository<Usuario,String> {

	Optional<Usuario> findUserByNumeroTelefono(String phoneNumber);
}
