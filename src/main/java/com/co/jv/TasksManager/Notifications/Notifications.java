package com.co.jv.TasksManager.Notifications;

import com.co.jv.TasksManager.utils.WhatsAppArgs;

import java.util.Date;
import java.util.List;

public interface Notifications<T> {


    void Send(List<T> args);
}
