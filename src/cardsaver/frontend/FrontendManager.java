package cardsaver.frontend;

import cardsaver.Controller;
import cardsaver.auth.AuthService;
import cardsaver.auth.AuthStatus;
import cardsaver.crypto.CryptoService;
import cardsaver.frontend.GUI.LoginRegister;
import cardsaver.frontend.GUI.WalletGUI;
import cardsaver.storage.CardsManager;
import cardsaver.storage.UsersManager;

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
    UsersManager usersmanager;

    public FrontendManager(Controller controller , AuthService authService, CardsManager cardsManager , CryptoService cryptoService , UsersManager usersManager) throws Exception {
        this.controller=controller;
        this.usersmanager=usersManager;
        this.authService=authService;
        this.cardsManager=cardsManager;
        this.cryptoService=cryptoService;
        usercards = new ArrayList<>();
        loginRegister = new LoginRegister(this);

    }

    public void register(String username , String firstname , String lastname , String email , String password) throws Exception {
        AuthStatus status = authService.createAccount(username, firstname, lastname, email, password); //ελεγχουμε στο frontend αν εχει γινει το register και επιστρέφουμε τα αναλογα error
       if(status==AuthStatus.SUCCESS) {
           usersmanager.saveAES(cryptoService.generateAES(),authService.getCurrentaccount());
           onauthorize();
       }else{
           JOptionPane.showMessageDialog(loginRegister.getContentPane(), status.getMessage());
       }
    }


    //μέθοδος που τρέχει μετα την αυθεντικοποίση και αρχικοποιεί το κυριως παράθυρο και μας μεταφέρει σε αυτό.
    public void onauthorize() throws Exception {


        ArrayList<CreditCard> initcards = cardsManager.getCardsFromFile(authService.getCurrentaccount());
        if(initcards==null)
            usercards= new ArrayList<>();
        else usercards=initcards;

        cardsManager.updateFileCards(usercards,authService.getCurrentaccount());


        loginRegister.setVisible(false);
        loginRegister.dispose();
        walletGUI = new WalletGUI(this);
        update();

        if(controller.checkintegrity()){ //ελεγχος ακεραιότητας
            JOptionPane.showConfirmDialog(walletGUI,"Someone intruded the system");
        }
    }
    public void login(String username , String password) throws Exception {
            AuthStatus status = authService.loginAccount(username,password);
            if(status==AuthStatus.SUCCESS){
              System.out.println("SUCCESS");
            }else{
                JOptionPane.showMessageDialog(loginRegister.getContentPane(), status.getMessage());
            }
        }

        public void edit(String owner , String number , String date , String cvv , String type,String id) throws Exception { //επεξέργασία στοιχείων αντικειμένου creditcard
        for(int i=0;i<usercards.size();i++){
            if(usercards.get(i).getId().equals(id)){
                usercards.get(i).setOwner(owner);
                usercards.get(i).setNumber(number);
                usercards.get(i).setExpirationDate(date);
                usercards.get(i).setCvv(cvv);
                usercards.get(i).setType(type);
            }

        }
        update();

        }

        public void delete(String id) throws Exception {
            for (int i=0;i<usercards.size();i++){ //διαγραφή αντικειμένου
                if(usercards.get(i).getId().equals(id))
                    usercards.remove(i);
            }
            update();
        }

        public void update() throws Exception { //ανανέωση αρχείου και λίστας
            cardsManager.updateFileCards(usercards,authService.getCurrentaccount());
            if(usercards!=null)
            walletGUI.updatelist(usercards);



        }

        public  void finalmethod() throws Exception { //τελευταία μέθοδος που καταλήγει στη δημιουργία κρυπτογραφημένου αρχείου ψηφιακών υπογραφών
        controller.finalmethod();
            System.exit(0);
        }

    public void addCard(String number , String owner , String cvv , String type , String expirationdate) throws Exception { //προσθήκη αντικειμένου κάρτας
       usercards.add(new CreditCard(number, owner, cvv, type , expirationdate));
        update();

    }


    }
