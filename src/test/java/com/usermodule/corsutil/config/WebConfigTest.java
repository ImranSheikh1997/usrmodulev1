package com.usermodule.corsutil.config;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@ContextConfiguration( classes = {WebConfig.class})
class WebConfigTest {

    @Mock
    CorsRegistry corsRegistry;

    @Test
    void is_addCorsMappings_allow_allowedOrigin() {
        String allowedOrigin = "http://localhost:4200";


    }
}