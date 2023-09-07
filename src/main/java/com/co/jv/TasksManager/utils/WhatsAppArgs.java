package com.co.jv.TasksManager.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WhatsAppArgs {
    private String numeroDestino;
    private String titulo;
    private Date fechaLimite;
}
