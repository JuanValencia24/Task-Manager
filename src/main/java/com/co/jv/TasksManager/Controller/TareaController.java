package com.co.jv.TasksManager.Controller;

import com.co.jv.TasksManager.DTO.TareaDTO;
import com.co.jv.TasksManager.Service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class TareaController {
    @Autowired
    private TareaService tareaService;


    @PostMapping("/task")
    public ResponseEntity<?> save(@RequestBody TareaDTO tareaDTO, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.badRequest().body("Register faild");
        }
        tareaService.save(tareaDTO);
        return ResponseEntity.ok("Register successful");
    }
    @GetMapping("/tasks/{usuarioId}")
    public ResponseEntity<?> getAllTasksByIdUser(@PathVariable String usuarioId){
        List<TareaDTO> tareaDTOS = tareaService.findTareasByUsuarioIdYEstadoDoneInProgress(usuarioId);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Responded","TaskController");
        return ResponseEntity.accepted().headers(headers).body(tareaDTOS);
    }

    @PutMapping("/task")
    public ResponseEntity<?> updateTask(@RequestBody TareaDTO tareaDTO,Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.badRequest().body("Update faild");
        }
        String response = tareaService.update(tareaDTO);
        if(response.equals("ERROR")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task no exist");
        }

        return ResponseEntity.ok("successful update");
    }

    @DeleteMapping("task/{id}")
    public ResponseEntity<?> deleteTaskById(@PathVariable String id){
        tareaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
