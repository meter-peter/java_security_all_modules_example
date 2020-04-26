package cardsaver.crypto;

import cardsaver.auth.Account;
import cardsaver.storage.CardsManager;
import cardsaver.storage.UsersManager;
import com.google.gson.Gson;
import javafx.util.Pair;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;

public class IntegrityChecker {
    CryptoService cryptoService;
    UsersManager usersManager;
    CardsManager cardsManager;
    HashMap<String, byte[]> pairs;
    ArrayList<byte[]> decryptedSigs;
    HashMap<String,byte[]> tocheckpairs;
    ArrayList<byte[]> tempsigs;


    public IntegrityChecker(CryptoService cryptoService, UsersManager usersManager, CardsManager cardsManager) {
        this.cryptoService = cryptoService;
        this.usersManager = usersManager;
        this.cardsManager = cardsManager;
        pairs = new HashMap<>();
        decryptedSigs =new ArrayList<>();
        tocheckpairs = new HashMap<>();
        tempsigs = new ArrayList<>();

    }

    public void processSHA(byte[] pairedjson, Account user) throws Exception {
        Signature sign = Signature.getInstance("SHA256withRSA"); //Αρχικοποιουμε την κλάση Signature με το Private key
        sign.initSign(cryptoService.getKeyPair().getPrivate());
        sign.update(pairedjson);
        byte[] realSig = sign.sign(); //δημιουργούμε τα bytes με την υπογραφή


        Cipher cipher = Cipher.getInstance("AES"); //αρχικοποιουμε την κλάση για να κρυπτογραφήσουμε την υπογραφή με το δημοσιο κλειδί τουυ χρήστη
        SecretKeySpec secretKeySpec = new SecretKeySpec(usersManager.getAES(user), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        byte[] encryptedsignature = cipher.doFinal(realSig);

        //γραφουμε την κρυπτογραφημένη υπογραφή του χρήστη στο σχετικό αρχείο
        File file = new File("users//" + user.getUsername() + "//encryptedsig.cryptSig");
        file.createNewFile();
        FileOutputStream sigfos = new FileOutputStream(file);
        sigfos.write(encryptedsignature);
        sigfos.close();

    }


    //με αυτή τη συνάρτηση κρυπτογραφούμε τα νεα pair <username,digest> για να τα συγκρίνουμε τις συνοψεις τους
    public byte[] SHA(byte[] pairedjson, Account user) throws Exception {
        Signature sign = Signature.getInstance("SHA256withRSA"); //
        sign.initSign(cryptoService.getKeyPair().getPrivate());
        sign.update(pairedjson);
        byte[] realSig = sign.sign();


        Cipher cipher = Cipher.getInstance("AES");
        SecretKeySpec secretKeySpec = new SecretKeySpec(usersManager.getAES(user), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        byte[] encryptedsignature = cipher.doFinal(realSig);
        tempsigs.add(encryptedsignature);

        return encryptedsignature;


    }
    public boolean checkintegrity() throws Exception {
        boolean flag =false;
        createTemppairs();
        if (usersManager.getUsers().size() > 0) {
            for (int i = 0; i < usersManager.getUsers().size(); i++) {
                decryptedSigs.add(getSig(usersManager.getUsers().get(i))); //συγκρίνουμε τα 2 ArrayList που περιέχουν πινακες bytes και αν έστω ενα ειναι πειραγμένο αλλαζουμε το flag σε true
            }
            for(int i = 0;i<decryptedSigs.size();i++){
                String tostring1=Base64.getEncoder().encodeToString(decryptedSigs.get(i));
                String tostring2=Base64.getEncoder().encodeToString(tempsigs.get(i));
                if(!tostring1.equals(tostring2)){
                    flag=true;

                }
            }

        }
        return true;
    }




    public byte[] getSig(Account user) throws Exception { //αποκρυπτογράφηση  των υπαρχοντων υπογραφών απο το αρχείο
        FileInputStream sigfis = new FileInputStream(new File("users//"+user.getUsername()+"//encryptedsig.cryptSig"));
        byte[] encryptedsig = sigfis.readAllBytes();
        Cipher cipher = Cipher.getInstance("AES");
        SecretKeySpec secretKeySpec = new SecretKeySpec(usersManager.getAES(user), "AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        return cipher.doFinal(encryptedsig);

    }

    public void createTemppairs() throws Exception { //Δημιουργία των νέων ζευγαριών <username,digest> τα οποιοα θα κροπτογραφήθουν για να συγκριθούν με τα υπάρχοντα.
        if (usersManager.getUsers().size() > 0) {
            for (int i = 0; i < usersManager.getUsers().size(); i++) {
                Pair<String, byte[]> temp = generatePair(usersManager.getUsers().get(i));
                tocheckpairs.put(temp.getKey(), temp.getValue());


            }


        }
        for (int i = 0; i < tocheckpairs.size(); i++) {
            Gson gson = new Gson();
            String json = gson.toJson(tocheckpairs.get(i));
            SHA(json.getBytes(), usersManager.getUsers().get(i));


        }
    }

   public void securefiles() throws Exception {
        if (usersManager.getUsers().size() > 0) {
            for (int i = 0; i < usersManager.getUsers().size(); i++) {
                Pair<String, byte[]> temp = generatePair(usersManager.getUsers().get(i));
                pairs.put(temp.getKey(), temp.getValue());


            }


        }
        for (int i = 0; i < pairs.size(); i++) {
            Gson gson = new Gson();
            String json = gson.toJson(pairs.get(i));
            processSHA(json.getBytes(), usersManager.getUsers().get(i));


        }
    }

    public Pair<String, byte[]> generatePair(Account user) throws NoSuchAlgorithmException, IOException {

        File file = new File("users//" + user.getUsername() + "//cards"); //ανακτηση των pairs απο το αρχείο
        byte[] bFile = Files.readAllBytes(Paths.get(file.getPath()));
        final MessageDigest digest = MessageDigest.getInstance("SHA-256");
        final byte[] hashbytes = digest.digest(bFile);
        Pair<String, byte[]> temp = new Pair(user.getUsername(), hashbytes);
        return temp;
    }


}
