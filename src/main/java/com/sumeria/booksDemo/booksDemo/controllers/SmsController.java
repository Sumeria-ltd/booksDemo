package com.sumeria.booksDemo.booksDemo.controllers;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SmsController {

    private final String accountSid;
    private final String authToken;

    public SmsController(@Value("twillio.accountSid") String accountSid, @Value("twillio.authToken") String authToken){
        this.accountSid = accountSid;
        this.authToken = authToken;
    }

    @PostMapping("/api/sms")
    //@PreAuthorize("hasRole('USER')")
    public void sendSms(){
//        Twilio.init(accountSid, authToken);
//        Message message = Message.creator(
//                        new com.twilio.type.PhoneNumber("+13343754260"),
//                        new com.twilio.type.PhoneNumber("+13343754260"),
//                        "Hello From Twilio!!")
//                .create();

//        System.out.println(message.getSid());

    }
}
