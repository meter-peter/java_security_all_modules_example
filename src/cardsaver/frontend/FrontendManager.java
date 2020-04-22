package cardsaver.frontend;

import cardsaver.Controller;
import cardsaver.frontend.GUI.LoginRegister;
import cardsaver.storage.FileManager;

import javax.swing.*;

public class FrontendManager {
    Controller controller;
    LoginRegister loginRegister;



    public FrontendManager(Controller controller){
        this.controller=controller;
        loginRegister = new LoginRegister(this);



    }



    public void registerUser(String username , String password){


    }

}
