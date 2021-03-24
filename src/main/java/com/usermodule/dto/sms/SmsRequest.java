package com.usermodule.dto.sms;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Getter
public class SmsRequest {
    @NotBlank
    private String mobileNumber;
    @NotBlank
    private String message;

}
