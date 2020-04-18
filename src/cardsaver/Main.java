package cardsaver;

import cardsaver.auth.AuthService;
import cardsaver.crypto.CryptoService;
import cardsaver.frontend.Frontend;
import cardsaver.storage.FileManager;

import java.io.*;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class Main {

    public static void main(String[] args) throws NoSuchAlgorithmException, IOException, InvalidKeySpecException {

        FileManager fileManager = new FileManager();
        AuthService authService = new AuthService();
        CryptoService cryptoService = new CryptoService();
        Frontend frontend = new Frontend();
        new Controller(authService,fileManager,frontend,cryptoService);
    }

}
