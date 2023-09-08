package com.co.jv.TasksManager.entity;

import com.co.jv.TasksManager.utils.Estado;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.util.Date;

@Document(collection = "tareas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tarea{
    @Id
    private String id;
    @NotNull
    private String titulo;
    @NotNull
    private String descripcion;
    @NotNull
    @Field("fecha_limite")
    private LocalDate fechaLimite;
    @Field("fecha_notificacion")
    private LocalDate fechaNotificacion;
    @NotNull
    @Field("usuario_id")
    private String usuarioId;
    @NotNull
    private Boolean notificame;
    @NotNull
    private Boolean notificado;
    @NotNull
    private Estado estado;
    @NotNull
    @CreatedDate
    @Field("fecha_creacion")
    private LocalDate fechaCreacion;
    @NotNull
    @LastModifiedDate
    @Field("fecha_actualizacion")
    private LocalDate fechaActualizacion;



}
