package cardsaver.crypto;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
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


    public  byte[] encryptWithAES(byte[] Data ,byte[] key) throws Exception {
        Key originalKey = new SecretKeySpec(key, 0, key.length, "AES");
        Cipher c = Cipher.getInstance("AES");
        c.init(Cipher.ENCRYPT_MODE,originalKey);
        byte[] encVal = c.doFinal(Data);
        return encVal;
    }


    public byte[] decryptWithAES(byte[] encryptedData , byte[] key) throws Exception {
        Key originalKey = new SecretKeySpec(key, 0, key.length, "AES");
        Cipher c = Cipher.getInstance("AES");
        c.init(Cipher.DECRYPT_MODE, originalKey);

        byte[] decValue = c.doFinal(encryptedData);
        return decValue;
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


