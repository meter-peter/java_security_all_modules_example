package cardsaver.crypto;

import javax.crypto.SecretKey;
import java.io.*;
import java.math.BigInteger;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

public class CryptoService {

    KeyPair keyPair;
    SecretKey secretKey;


    public CryptoService() throws NoSuchAlgorithmException, IOException, InvalidKeySpecException {
        initKeyPair();
    }

    public KeyPair generatekeypair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        keyPair = keyPairGenerator.generateKeyPair();
        return keyPair;
    }

   private void initKeyPair() throws NoSuchAlgorithmException, IOException, InvalidKeySpecException {
        File privatekeyfile = new File("PRIVATEKEY");
        File publickeyfile = new File("PUBLICKEY");

        if(!privatekeyfile.exists()) {
            privatekeyfile.createNewFile();
            publickeyfile.createNewFile();
            savekeys(privatekeyfile,publickeyfile,generatekeypair());
        }
        else{
            keyPair=new KeyPair(readPublicKey(publickeyfile),readPrivateKey(privatekeyfile));
        }
    }

    private static PublicKey readPublicKey(File publickeyfile) throws IOException {
        InputStream in = new FileInputStream(publickeyfile);
        ObjectInputStream oin =
                new ObjectInputStream(new BufferedInputStream(in));
        try {
            BigInteger m = (BigInteger) oin.readObject();
            BigInteger e = (BigInteger) oin.readObject();
            RSAPublicKeySpec keySpec = new RSAPublicKeySpec(m, e);
            KeyFactory fact = KeyFactory.getInstance("RSA");
            PublicKey pubKey = fact.generatePublic(keySpec);
            return pubKey;
        } catch (Exception e) {
            throw new IOException(e);
        } finally {
            oin.close();
        }
    }
    private static PrivateKey readPrivateKey(File privatekeyfile) throws IOException {
        InputStream in = new FileInputStream(privatekeyfile);
        ObjectInputStream oin = new ObjectInputStream(new BufferedInputStream(in));
        try {
            BigInteger m = (BigInteger) oin.readObject();
            BigInteger e = (BigInteger) oin.readObject();
            RSAPublicKeySpec keySpec = new RSAPublicKeySpec(m, e);
            KeyFactory fact = KeyFactory.getInstance("RSA");
            PrivateKey privKey = fact.generatePrivate(keySpec);
            return privKey;
        } catch (Exception e) {
            throw new IOException(e);
        } finally {
            oin.close();
        }
    }

    public void savekeys (File privatekeyfile , File publickeyfile,KeyPair keyPair) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        RSAPublicKeySpec pub = keyFactory.getKeySpec(keyPair.getPublic(), RSAPublicKeySpec.class);
        saveToFile(publickeyfile.getName(), pub.getModulus(), pub.getPublicExponent());
        RSAPrivateKeySpec priv = keyFactory.getKeySpec(keyPair.getPrivate(), RSAPrivateKeySpec.class);
        saveToFile(privatekeyfile.getName(), priv.getModulus(), priv.getPrivateExponent());
    }

    private static void saveToFile(String fileName, BigInteger mod, BigInteger exp) throws IOException {
        ObjectOutputStream oout = new ObjectOutputStream(   new BufferedOutputStream(new FileOutputStream(fileName)));
        try {
            oout.writeObject(mod);
            oout.writeObject(exp);
        } catch (Exception e) {
            throw new IOException(e);
        } finally {
            oout.close();
        }
    }

    }

