package com.co.jv.TasksManager.DTO;


import com.co.jv.TasksManager.utils.Estado;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TareaDTO {
    private String id;
    private String titulo;
    private String descripcion;
    private Date fechaLimite;
    private Date fechaNotificacion;
    private String usuarioId;
    private Boolean notificame;
    private Boolean notificado;
    private Estado estado;
    private Date fechaCreacion;
    private Date fechaActualizacion;

    public TareaDTO(String titulo, String descripcion, Date fechaLimite, Date fechaNotificacion,
                    String usuarioId, Boolean notificame, Boolean notificado, Date fechaCreacion,
                    Date fechaActualizacion) {
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
