package cardsaver.frontend;

import cardsaver.Controller;
import cardsaver.auth.AuthService;
import cardsaver.frontend.GUI.LoginRegister;

import java.security.NoSuchAlgorithmException;

public class FrontendManager {
    Controller controller;
    LoginRegister loginRegister;
    AuthService authService;



    public FrontendManager(Controller controller , AuthService authService){
        this.controller=controller;
        this.authService=authService;
        loginRegister = new LoginRegister(this);



    }

    public void register(String username , String firstname , String lastname , String email , String password) throws Exception {
        authService.createAccount(username, firstname, lastname, email, password);
        }

        public void login(String username , String password) throws Exception {
        authService.loginAccount(username,password);
        }








    }
