package cardsaver;

import cardsaver.auth.AuthService;
import cardsaver.crypto.CryptoService;
import cardsaver.frontend.FrontendManager;
import cardsaver.storage.FileManager;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class Controller {

    public Controller() throws NoSuchAlgorithmException, IOException, InvalidKeySpecException {
        FileManager fileManager = new FileManager();
        AuthService authService = new AuthService();
        CryptoService cryptoService = new CryptoService();
        FrontendManager frontendManager = new FrontendManager(this);

    }

    public void register(){


    }


}
