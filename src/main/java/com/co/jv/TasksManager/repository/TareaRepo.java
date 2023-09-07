package com.co.jv.TasksManager.repository;

import com.co.jv.TasksManager.entity.Tarea;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface TareaRepo extends MongoRepository<Tarea,String> {
    List<Tarea> findByUsuarioId(String usuarioId);

    @Query("{'fecha_notificacion' : ?0, 'notificame' : true}")
    Optional<List<Tarea>> findTareasByFechaNotificacionAndNotificame(Date fechaNotificacion);

    @Query("'fecha_limite' : ?0, 'notificado' : false")
    Optional<List<Tarea>> findTareasByFechaLimiteActual(Date fechaLimite);
    @Query("{'usuario_id' : ?0, 'estado' : {$in : ['DONE', 'IN_PROGRESS']}}")
    List<Tarea> findTareasByUsuarioIdAndEstado(String usuarioId);
    @Query("'fecha_limite' : ?0, 'estado' : 'DONE'")
    Optional<List<Tarea>> findTareasByFechaLimiteActualAndEstadoDone(Date fechaLimite);

}
