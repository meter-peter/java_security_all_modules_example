package cardsaver.frontend;

import cardsaver.Controller;
import cardsaver.auth.AuthService;
import cardsaver.auth.AuthStatus;
import cardsaver.frontend.GUI.LoginRegister;
import cardsaver.storage.UsersManager;

import javax.swing.*;
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
        AuthStatus status = authService.createAccount(username, firstname, lastname, email, password);
       if(status==AuthStatus.SUCCESS) {
           authorize();


       }else{
           JOptionPane.showMessageDialog(loginRegister.getContentPane(), status.getMessage());

       }
    }

    private void authorize(){
        controller.continuewithinapp(authService.getCurrentaccount());
        loginRegister.setVisible(false);
        loginRegister.dispose();


    }

        public void login(String username , String password) throws Exception {
            AuthStatus status = authService.loginAccount(username,password);
            if(status==AuthStatus.SUCCESS){
               authorize();
            }else{
                JOptionPane.showMessageDialog(loginRegister.getContentPane(), status.getMessage());
            }
        }








    }
