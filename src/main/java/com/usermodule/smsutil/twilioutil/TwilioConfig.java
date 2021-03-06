package com.usermodule.smsutil.twilioutil;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "twilio")
@NoArgsConstructor
@Getter
@Setter
public class TwilioConfig {
    private String account_sid;
    private String auth_token;
    private String trial_number;

}
