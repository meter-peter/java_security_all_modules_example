package cardsaver.crypto;

import javax.crypto.Cipher;
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

    public  byte[] encryptSaltedHash(byte[] Data) throws Exception {
        Cipher c = Cipher.getInstance("RSA");
        c.init(Cipher.ENCRYPT_MODE,getKeyPair().getPublic());
        byte[] encVal = c.doFinal(Data);
        return encVal;
    }

    public byte[] decryptPassword(byte[] encryptedData ) throws Exception {
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



}

