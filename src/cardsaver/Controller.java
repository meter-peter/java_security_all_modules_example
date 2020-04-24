package cardsaver;

import cardsaver.auth.Account;
import cardsaver.auth.AuthService;
import cardsaver.crypto.CryptoService;
import cardsaver.frontend.FrontendManager;
import cardsaver.storage.CardsManager;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class Controller {
    CardsManager cardsManager;
    AuthService authService;
    CryptoService cryptoService;
    FrontendManager frontendManager;
    Account currentUser;

    public Controller() throws NoSuchAlgorithmException, IOException, InvalidKeySpecException {
        cardsManager = new CardsManager();
        authService = new AuthService(this);
        cryptoService = new CryptoService();
        frontendManager = new FrontendManager(this,authService);



    }

 public void continuewithinapp(Account account){
        currentUser = account;

 }


}
