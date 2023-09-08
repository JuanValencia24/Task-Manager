package com.co.jv.TasksManager.Notifications;

import com.co.jv.TasksManager.utils.WhatsAppArgs;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WhatsApp implements Notifications<WhatsAppArgs>{

    @Value("${twilio.account-sid}")
    private String ACCOUNT_SID;
    @Value("${twilio.auth-token}")
    private String AUTHTOKEN;
    @Value("${twilio.number-local}")
    private String NUMBERLOCAL;



    @Override
    public void Send(WhatsAppArgs args) {
        Twilio.init(ACCOUNT_SID, AUTHTOKEN);
        String titulos = String.join(", ",args.getTitulo());
            Message message = Message.creator(
                    new PhoneNumber(args.getNumeroDestino()),
                    new PhoneNumber(NUMBERLOCAL),
                    "Tu tarea : "+
                            titulos+"  esta para el "+
                            args.getFechaLimite()+"."
            ).create();
    }

}


