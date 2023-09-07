package com.co.jv.TasksManager.Notifications;

import com.co.jv.TasksManager.utils.WhatsAppArgs;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class WhatsApp implements Notifications<WhatsAppArgs>{

    @Value("${twilio.account-sid}")
    private String ACCOUNT_SID;
    @Value("${twilio.auth-token}")
    private String AUTHTOKEN;
    @Value("${twilio.number-local}")
    private String NUMBERLOCAL;

    @Override
    public void Send(List<WhatsAppArgs> args) {
        Twilio.init(ACCOUNT_SID, AUTHTOKEN);
        for (WhatsAppArgs arg : args) {
            Message message = Message.creator(
                    new PhoneNumber(arg.getNumeroDestino()),
                    new PhoneNumber(NUMBERLOCAL),
                    "Hola te avisamos que tienes "+
                            arg.getTitulo()+" para el "+
                            arg.getFechaLimite()+"."
            ).create();
        }

    }

}
