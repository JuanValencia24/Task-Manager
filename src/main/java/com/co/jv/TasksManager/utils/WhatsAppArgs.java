package com.co.jv.TasksManager.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WhatsAppArgs {
    private String numeroDestino;
    private List<String> titulo;
    private LocalDate fechaLimite;
}
