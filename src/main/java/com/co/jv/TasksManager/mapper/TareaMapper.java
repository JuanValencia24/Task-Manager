package com.co.jv.TasksManager.mapper;

import com.co.jv.TasksManager.DTO.TareaDTO;
import com.co.jv.TasksManager.entity.Tarea;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TareaMapper {
    @Mapping(target = "id",ignore = true)
    @Mapping(target = "fechaCreacion",ignore = true)
    @Mapping(target = "fechaActualizacion",ignore = true)
    Tarea tareaDTOToTarea(TareaDTO tareaDTO);

    TareaDTO tareaToTareaDTO(Tarea tarea);
}
