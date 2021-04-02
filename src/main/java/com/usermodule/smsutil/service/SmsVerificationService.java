package com.usermodule.smsutil.service;

import com.usermodule.smsutil.dto.SmsRequest;
import com.usermodule.smsutil.dto.SmsSender;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SmsVerificationService {

    private SmsSender smsSender;

    public void sendSms(SmsRequest smsRequest){
        smsSender.sendSms(smsRequest);
    }
}
