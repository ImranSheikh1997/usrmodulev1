package com.usermodule.emailverificationutil.service;

import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mail.javamail.JavaMailSender;

@RunWith(MockitoJUnitRunner.class)
class EmailServiceTest{

    @Rule
    private ExpectedException thrown = ExpectedException.none();

    @Mock
    JavaMailSender javaMailSender;


    @Before
    public void setUp(){

    }
    @Test
    void send() {
    }
}