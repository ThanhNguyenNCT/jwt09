package com.Cybersoft.uniclub09.services;

public interface AuthenticationServices {
    String checkLogin(String email, String password);
    void signup(String email, String password, String fullName);
}
