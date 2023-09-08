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
        WhatsAppArgs infoNotificacion = new WhatsAppArgs();
        List<String> titulos = new ArrayList<>();
        LocalDate fechaActual=LocalDate.now();
        Optional<List<Tarea>> tareas = tareaRepo.findTareasByFechaNotificacionAndNotificame(fechaActual);
        if(tareas.isPresent()){
            for (Usuario usuario:usuarios){
                for (Tarea tarea:tareas.get()){
                    if(usuario.getId().equals(tarea.getUsuarioId())){
                        infoNotificacion.setNumeroDestino("whatsapp:"+usuario.getCodigoPais()+usuario.getNumeroTelefono());
                        titulos.add(tarea.getTitulo());
                        infoNotificacion.setFechaLimite(tarea.getFechaLimite());
                    }

                }
                infoNotificacion.setTitulo(titulos);
                whatsApp.Send(infoNotificacion);
                titulos.clear();
            }

        }

    }
}
