package com.usermodule.emailverificationutil.service;

public interface EmailSender {
    void send(String to, String email);
}
