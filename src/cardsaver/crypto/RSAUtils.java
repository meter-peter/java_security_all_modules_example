package cardsaver.crypto;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RSAUtils {
    public RSAUtils(){
    }
    public static PublicKey getPublickey() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        FileInputStream keyfis = new FileInputStream("publickey");
        byte[] encKey = new byte[keyfis.available()];
        keyfis.read(encKey);

        keyfis.close();
        X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(encKey);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey pubKey = keyFactory.generatePublic(pubKeySpec);
        return pubKey;

    }

    public static PrivateKey getPrivatekey() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        FileInputStream keyfis = new FileInputStream("privatekey");
        byte[] encKey = new byte[keyfis.available()];
        keyfis.read(encKey);

        keyfis.close();
        PKCS8EncodedKeySpec privkeyspec = new PKCS8EncodedKeySpec(encKey);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privkey = keyFactory.generatePrivate(privkeyspec);
        return privkey;
}
    }
