package com.co.jv.TasksManager.entity;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "usuarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    private String id;
    @NotNull
    @Field("primer_nombre")
    private String primerNombre;
    @Field("segundo_nombre")
    private String segundoNombre;
    @NotNull
    @Field("primer_apellido")
    private String primerApellido;
    @Field("segundo_apellido")
    private String segundoApellido;
    @NotNull
    @Field("codigo_pais")
    private String codigoPais;
    @NotNull
    @Field("numero_telefono")
    private String numeroTelefono;
    @NotNull
    private String clave;


}
