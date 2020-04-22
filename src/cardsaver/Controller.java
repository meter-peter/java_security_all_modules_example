package cardsaver;

import cardsaver.auth.Account;
import cardsaver.auth.AuthService;
import cardsaver.crypto.CryptoService;
import cardsaver.frontend.FrontendManager;
import cardsaver.storage.FileManager;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class Controller {
    FileManager fileManager;
    AuthService authService;
    CryptoService cryptoService;
    FrontendManager frontendManager;
    Account currentUser;

    public Controller() throws NoSuchAlgorithmException, IOException, InvalidKeySpecException {
        fileManager = new FileManager();
        authService = new AuthService();
        cryptoService = new CryptoService();
        frontendManager = new FrontendManager(this);



    }

    public void register(){


    }


}
