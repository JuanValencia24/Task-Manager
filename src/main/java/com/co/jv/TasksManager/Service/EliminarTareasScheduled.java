package com.co.jv.TasksManager.Service;

import com.co.jv.TasksManager.entity.Tarea;
import com.co.jv.TasksManager.repository.TareaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EliminarTareasScheduled {
    @Autowired
    private TareaRepo tareaRepo;

    @Scheduled(cron = "0 0 0 * * *")
    public void EliminarTareaConEstadoDelete(){
        List<Tarea> tareasEliminar;
        LocalDate fechaActual=LocalDate.now();
        Optional<List<Tarea>> optionalTareas = tareaRepo.findTareasByFechaLimiteActualAndEstadoDone(fechaActual);
        if(optionalTareas.isPresent()){
            tareasEliminar = optionalTareas.get();
            for(Tarea tarea : tareasEliminar){
                if(tarea.getFechaLimite().plusDays(7).equals(fechaActual)){
                    tareaRepo.deleteById(tarea.getId());
                }
            }

        }
    }
}
