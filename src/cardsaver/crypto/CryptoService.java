package cardsaver.crypto;

import javax.crypto.SecretKey;
import java.io.*;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.*;
import java.security.spec.*;
import java.util.Base64;

public class CryptoService {

    KeypairGenerator keypairGenerator;
    KeyPair keyPair;


    public CryptoService() throws NoSuchAlgorithmException, IOException, InvalidKeySpecException {
        keypairGenerator = new KeypairGenerator();
        keyPair = keypairGenerator.getKeyPair();
        System.out.println(keyPair.toString());
    }


}

