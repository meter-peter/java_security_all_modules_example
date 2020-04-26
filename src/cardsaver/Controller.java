package cardsaver;

import cardsaver.auth.Account;
import cardsaver.auth.AuthService;
import cardsaver.crypto.CryptoService;
import cardsaver.frontend.FrontendManager;
import cardsaver.storage.CardsManager;
import cardsaver.storage.UsersManager;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class Controller {
    CardsManager cardsManager;
    AuthService authService;
    CryptoService cryptoService;
    FrontendManager frontendManager;
    Account currentUser;
    UsersManager usersManager;


    public Controller() throws Exception {
        cryptoService = new CryptoService();

        usersManager = new UsersManager(cryptoService);
        authService = new AuthService(this,usersManager);
        cardsManager = new CardsManager(cryptoService,usersManager);
        frontendManager = new FrontendManager(this,authService,cardsManager,cryptoService);



    }

 public void continuewithinapp(Account account){
        currentUser = account;
        usersManager.setCurrent(account);
        usersManager.createUserDrectory(currentUser);
        frontendManager.opengui();



 }



}
