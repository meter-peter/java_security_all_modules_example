package cardsaver.storage;

import cardsaver.auth.Account;
import cardsaver.crypto.CryptoService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class UsersManager {

    Account current;
    File users;
    CryptoService cryptoService;

public UsersManager(CryptoService cryptoService) throws IOException, InvalidKeySpecException, NoSuchAlgorithmException {
    this.cryptoService = new CryptoService();
    users = new File("users.json");
    if(!users.exists())
        users.createNewFile();
    new File("users").mkdir();

}

    public Account getCurrent() {
        return current;
    }

    public void setCurrent(Account current) {
        this.current = current;
    }

    public ArrayList<Account> getUsers(){ //μετατροπή απο json αρχειο σε arraylist
        try {
            Reader reader = Files.newBufferedReader(Paths.get(users.getPath()));

            Type listType = new TypeToken<ArrayList<Account>>() {}.getType();
            return new Gson().fromJson(reader, listType);
        } catch (IOException e) {e.printStackTrace();

        }
        ArrayList<Account> temp = new ArrayList<>();
        return temp;
    }

    public void updateUsers(List<Account> appusers){ //ανανέωση του αρχείου
        try {
            Writer writer = Files.newBufferedWriter(Paths.get(users.getPath()));
            Gson gson = new Gson();
            gson.toJson(appusers, writer);
            writer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
}
    public void createUserDrectory(Account user){
        new File("users//"+user.getUsername()).mkdirs();

    }

    public void saveAES(byte[] data ,Account account) throws Exception { //αποθηκευση του AES κλειδιου του χρήστη σε αρχείο
        File key = new File("users//"+account.getUsername()+"//encryptedData.dat");
        if(!key.exists())
        key.createNewFile();
        byte[] encrypteddata = cryptoService.encryptWithRSA(data);

        try (FileOutputStream fos = new FileOutputStream(key)) {
            fos.write(encrypteddata);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public byte[] getAES(Account account) throws Exception { //αποκρυπτογράφηση του κλειδίου με το private key της εφαρμογής
        File key = new File("users//"+account.getUsername()+"//encryptedData.dat");
        byte[] bFile = Files.readAllBytes(Paths.get(key.getPath()));
        byte[] aes = cryptoService.decryptWithRSA(bFile);

        return aes;
    }
}
