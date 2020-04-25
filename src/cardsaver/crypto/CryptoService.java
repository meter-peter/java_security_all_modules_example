package cardsaver.crypto;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.*;

public class CryptoService {

    KeypairGenerator keypairGenerator;
    KeyPair keyPair;


    public CryptoService() throws NoSuchAlgorithmException, IOException, InvalidKeySpecException {
        keypairGenerator = new KeypairGenerator();
        keyPair = keypairGenerator.getKeyPair();

    }

public KeyPair getKeyPair(){
        return keyPair;
}

    public  byte[] encryptWithRSA(byte[] Data) throws Exception {
        Cipher c = Cipher.getInstance("RSA");
        c.init(Cipher.ENCRYPT_MODE,getKeyPair().getPublic());
        byte[] encVal = c.doFinal(Data);
        return encVal;
    }

    public byte[] decryptWithRSA(byte[] encryptedData ) throws Exception {
        Cipher c = Cipher.getInstance("RSA");
        c.init(Cipher.DECRYPT_MODE, getKeyPair().getPrivate());

        byte[] decValue = c.doFinal(encryptedData);
        return decValue;
    }

public byte[] generateSaltedHash(String passwordToHash , byte[] salt) throws NoSuchAlgorithmException {
    MessageDigest md = MessageDigest.getInstance("SHA-512");
    md.update(salt);
    byte[] hashedPassword = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
    return hashedPassword;
}

    public byte[] generateAES() throws NoSuchAlgorithmException {
        Key key;
        SecureRandom rand = new SecureRandom();
        KeyGenerator generator = KeyGenerator.getInstance("AES");
        generator.init(256, rand);
        key = generator.generateKey();
        return  key.getEncoded();
    }

}


