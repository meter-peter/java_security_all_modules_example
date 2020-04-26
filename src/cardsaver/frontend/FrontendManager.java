package cardsaver.frontend;

import cardsaver.Controller;
import cardsaver.auth.AuthService;
import cardsaver.auth.AuthStatus;
import cardsaver.crypto.CryptoService;
import cardsaver.frontend.GUI.LoginRegister;
import cardsaver.frontend.GUI.WalletGUI;
import cardsaver.storage.CardsManager;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;

public class FrontendManager {
    Controller controller;
    LoginRegister loginRegister;
    AuthService authService;
    WalletGUI walletGUI;
    CardsManager cardsManager;
    CryptoService cryptoService;
    ArrayList<CreditCard> usercards;

    public FrontendManager(Controller controller , AuthService authService, CardsManager cardsManager , CryptoService cryptoService) throws Exception {
        this.controller=controller;
        this.authService=authService;
        this.cardsManager=cardsManager;
        this.cryptoService=cryptoService;
        usercards = new ArrayList<>();
        loginRegister = new LoginRegister(this);

    }

    public void register(String username , String firstname , String lastname , String email , String password) throws Exception {
        AuthStatus status = authService.createAccount(username, firstname, lastname, email, password);
       if(status==AuthStatus.SUCCESS) {
           onauthorize();
       }else{
           JOptionPane.showMessageDialog(loginRegister.getContentPane(), status.getMessage());
       }
    }
    private void onauthorize() throws Exception {
        ArrayList<CreditCard> initcards = cardsManager.getCardsFromFile(authService.getCurrentaccount());
        if(initcards==null)
            usercards= new ArrayList<>();
        else usercards=initcards;

        addCard("187987098709870987","2","3","VISA","25-3-1999");
        cardsManager.updateFileCards(usercards,authService.getCurrentaccount());


        loginRegister.setVisible(false);
        loginRegister.dispose();
        update();
    }
    public void login(String username , String password) throws Exception {
            AuthStatus status = authService.loginAccount(username,password);
            if(status==AuthStatus.SUCCESS){
               onauthorize();
            }else{
                JOptionPane.showMessageDialog(loginRegister.getContentPane(), status.getMessage());
            }
        }
        public void opengui(){
        walletGUI = new WalletGUI(this);

        }

        public void update(){
            walletGUI.updatelist(usercards);

        }

    public void addCard(String number , String owner , String cvv , String type , String expirationdate) throws Exception {
       usercards.add(new CreditCard(number, owner, cvv, type , expirationdate));
        cardsManager.updateFileCards(cardsManager.getCreditCards(),authService.getCurrentaccount());

    }


    }
