package com.co.jv.TasksManager.Service;

import com.co.jv.TasksManager.Notifications.WhatsApp;
import com.co.jv.TasksManager.entity.Tarea;
import com.co.jv.TasksManager.entity.Usuario;
import com.co.jv.TasksManager.repository.TareaRepo;
import com.co.jv.TasksManager.repository.UsuarioRepo;
import com.co.jv.TasksManager.utils.WhatsAppArgs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationsWhatsAppScheduled {

    @Autowired
    private TareaRepo tareaRepo;
    @Autowired
    private UsuarioRepo usuarioRepo;
    @Autowired
    private WhatsApp whatsApp;


    @Scheduled(cron = "0 0 6 * * *")
    public void sendNotificationsWhatsApp(){
        List<Usuario> usuarios = usuarioRepo.findAll();
        List<WhatsAppArgs> infoNotificacion=new ArrayList<>();
        LocalDate fechaActual=LocalDate.now();
        Date fechaFormateada=Date.from(fechaActual.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Optional<List<Tarea>> tareas = tareaRepo.findTareasByFechaNotificacionAndNotificame(fechaFormateada);

        if(tareas.isPresent()){
            for (Tarea tarea:tareas.get()){
                for (Usuario usuario:usuarios){
                    if(tarea.getUsuarioId().equals(usuario.getId())){
                        for(WhatsAppArgs info:infoNotificacion){
                            info.setNumeroDestino(usuario.getCodigoPais()+usuario.getNumeroTelefono());
                            info.setTitulo(tarea.getTitulo());
                            info.setFechaLimite(tarea.getFechaLimite());
                            whatsApp.Send(infoNotificacion);
                            infoNotificacion.clear();
                        }
                    }
                }
            }

        }
    }
}
