package cardsaver.crypto;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;

public class KeypairGenerator {

        private PrivateKey privateKey;
        private PublicKey publicKey;
        private RSAUtils rsaUtils;
        private KeyPair keyPair;

        public KeypairGenerator() throws NoSuchAlgorithmException, IOException, InvalidKeySpecException {
            File pubfile = new File("publicKey");
            File privfile = new File("privateKey");
            rsaUtils = new RSAUtils();

            if(!pubfile.exists()){
                KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
                keyGen.initialize(2048);
                KeyPair pair = keyGen.generateKeyPair();
                this.privateKey = pair.getPrivate();
                this.publicKey = pair.getPublic();
                writeToFile(pubfile, publicKey.getEncoded());
                writeToFile(privfile, privateKey.getEncoded());
            }
           else{
               this.privateKey=rsaUtils.getPrivatekey();
               this.publicKey=rsaUtils.getPublickey();
            }
            keyPair=new KeyPair(getPublicKey(),getPrivateKey());
        }

        public void generate(){

        }

    public KeyPair getKeyPair() {
        return keyPair;
    }

    public static void writeToFile(File path, byte[] key) throws IOException {
            FileOutputStream keyfos = new FileOutputStream(path);
            keyfos.write(key);
            keyfos.close();
        }

        public PrivateKey getPrivateKey() {
            return privateKey;
        }

        public PublicKey getPublicKey() {
            return publicKey;
        }


}
