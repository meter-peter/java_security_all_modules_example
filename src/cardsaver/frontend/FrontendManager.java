package cardsaver.frontend;

import cardsaver.Controller;
import cardsaver.auth.Account;
import cardsaver.auth.AuthService;
import cardsaver.frontend.GUI.LoginRegister;
import cardsaver.storage.FileManager;

import javax.swing.*;
import java.util.List;

public class FrontendManager {
    Controller controller;
    LoginRegister loginRegister;
    AuthService authService;



    public FrontendManager(Controller controller , AuthService authService){
        this.controller=controller;
        this.authService=authService;
        loginRegister = new LoginRegister(this);



    }






}
