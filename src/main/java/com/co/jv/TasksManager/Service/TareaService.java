package com.co.jv.TasksManager.Service;

import com.co.jv.TasksManager.DTO.TareaDTO;
import com.co.jv.TasksManager.Notifications.WhatsApp;
import com.co.jv.TasksManager.Service.CaseUse.TareaCasoDeUso;
import com.co.jv.TasksManager.entity.Tarea;
import com.co.jv.TasksManager.entity.Usuario;
import com.co.jv.TasksManager.mapper.TareaMapper;
import com.co.jv.TasksManager.repository.TareaRepo;
import com.co.jv.TasksManager.repository.UsuarioRepo;
import com.co.jv.TasksManager.utils.Estado;
import com.co.jv.TasksManager.utils.WhatsAppArgs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TareaService implements TareaCasoDeUso {
    @Autowired
    private TareaRepo tareaRepo;

    @Autowired
    private TareaMapper tareaMapper;

    @Override
    @Transactional
    public void save(TareaDTO tareaDTO) {
        tareaRepo.save(tareaMapper.tareaDTOToTarea(tareaDTO));
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        tareaRepo.deleteById(id);
    }

    @Override
    @Transactional
    public String update(TareaDTO tareaDTO) {
        Optional<Tarea> optionalTarea = tareaRepo.findById(tareaDTO.getId());
        if(optionalTarea.isPresent()){
            tareaRepo.save(tareaMapper.tareaDTOToTarea(tareaDTO));
            return "Ok";
        }else{
            return "ERROR";
        }
    }

    @Override
    public List<TareaDTO> findTareasByUsuarioId(String usuarioId) {
        List<TareaDTO> tareaDTOS = new ArrayList<>();
        List<Tarea> tareas = tareaRepo.findByUsuarioId(usuarioId);
        for(Tarea tarea : tareas ){
            tareaDTOS.add(tareaMapper.tareaToTareaDTO(tarea));
        }
        return tareaDTOS;
    }
    @Override
    public List<TareaDTO> findTareasByUsuarioIdYEstadoDoneInProgress(String usuarioId){
       List<Tarea> tareas = tareaRepo.findTareasByUsuarioIdAndEstado(usuarioId);
       List<TareaDTO> tareaDTOS = new ArrayList<>();
       for(Tarea tarea : tareas){
           tareaDTOS.add(tareaMapper.tareaToTareaDTO(tarea));
       }
       return tareaDTOS;
    }


}
