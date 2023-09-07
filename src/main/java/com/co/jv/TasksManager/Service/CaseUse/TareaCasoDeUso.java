package com.co.jv.TasksManager.Service.CaseUse;
import com.co.jv.TasksManager.DTO.TareaDTO;
import com.co.jv.TasksManager.entity.Tarea;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;
import java.util.Optional;

public interface TareaCasoDeUso {

    void save(TareaDTO tareaDTO);

    void deleteById(String id);

    String update(TareaDTO tareaDTO);

    List<TareaDTO> findTareasByUsuarioId(String usuarioId);

    List<TareaDTO> findTareasByUsuarioIdYEstadoDoneInProgress(String usuarioId);


}
