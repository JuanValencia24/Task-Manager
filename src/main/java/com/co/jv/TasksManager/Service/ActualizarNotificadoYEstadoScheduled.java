package com.co.jv.TasksManager.Service;

import com.co.jv.TasksManager.entity.Tarea;
import com.co.jv.TasksManager.repository.TareaRepo;
import com.co.jv.TasksManager.utils.Estado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ActualizarNotificadoYEstadoScheduled {
    @Autowired
    private TareaRepo tareaRepo;

    @Scheduled(cron = "0 0 0 * * *")
    public void actualizarNotificadoTrueYEstadoDone(){
        List<Tarea> tareasModificar;
        LocalDate fechaActual=LocalDate.now();
        Date fechaFormateada=Date.from(fechaActual.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Optional<List<Tarea>> optionalTareas=tareaRepo.findTareasByFechaLimiteActual(fechaFormateada);

        if(optionalTareas.isPresent()){
            tareasModificar=optionalTareas.get();
            for(Tarea tarea: tareasModificar){
                tarea.setNotificado(true);
                tarea.setEstado(Estado.DONE);
                tareaRepo.save(tarea);
            }
        }
    }
}
