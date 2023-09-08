package com.co.jv.TasksManager.DTO;


import com.co.jv.TasksManager.utils.Estado;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TareaDTO {
    private String id;
    private String titulo;
    private String descripcion;
    private LocalDate fechaLimite;
    private LocalDate fechaNotificacion;
    private String usuarioId;
    private Boolean notificame;
    private Boolean notificado;
    private Estado estado;
    private LocalDate fechaCreacion;
    private LocalDate fechaActualizacion;

    public TareaDTO(String titulo, String descripcion, LocalDate fechaLimite, LocalDate fechaNotificacion,
                    String usuarioId, Boolean notificame, Boolean notificado, LocalDate fechaCreacion,
                    LocalDate fechaActualizacion) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaLimite = fechaLimite;
        this.fechaNotificacion = fechaNotificacion;
        this.usuarioId = usuarioId;
        this.notificame = notificame;
        this.notificado = notificado;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
    }
}
