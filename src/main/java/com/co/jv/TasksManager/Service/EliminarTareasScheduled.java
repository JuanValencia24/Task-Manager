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
    public void EliminarTareaConEstadoDone(){
        List<Tarea> tareasEliminar;
        LocalDate fechaHaceSieteDias=LocalDate.now().minusDays(7);
        System.out.println(fechaHaceSieteDias);
        Optional<List<Tarea>> optionalTareas = tareaRepo.findTareasByFechaLimiteActualAndEstadoDone(fechaHaceSieteDias);

        if(optionalTareas.isPresent()){
            tareasEliminar = optionalTareas.get();
            for(Tarea tarea : tareasEliminar){
                    tareaRepo.deleteById(tarea.getId());

            }

        }
    }
}
