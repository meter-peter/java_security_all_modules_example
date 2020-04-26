package cardsaver.storage;

import cardsaver.auth.Account;
import cardsaver.crypto.CryptoService;
import cardsaver.frontend.CreditCard;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileOutputStream;

import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;


public class CardsManager {

    ArrayList<CreditCard> creditCards;
    CryptoService cryptoService;
    UsersManager usersManager;


    public CardsManager(CryptoService cryptoService, UsersManager usersManager){
        this.cryptoService = cryptoService;
        this.usersManager=usersManager;


    }


    public ArrayList<CreditCard> getCreditCards() {
        return creditCards;
    }



    public ArrayList<CreditCard> getCardsFromFile(Account account) throws Exception { //αποκρυπτογράφηση του json αρχείου που περιέχει τις κάρτες και cast απο json σε Arraylist
        File file = new File("users//"+account.getUsername()+"//cards");
        if(!file.exists())
            file.createNewFile();
        byte[] bFile = Files.readAllBytes(Paths.get(file.getPath()));

        byte[] jsonbytes = cryptoService.decryptWithAES(bFile,usersManager.getAES(usersManager.getCurrent()));
        String jsonString = new String(jsonbytes, StandardCharsets.UTF_8);

        Type listType = new TypeToken<ArrayList<CreditCard>>() {}.getType();
        return new Gson().fromJson(jsonString, listType);




    }
    public void updateFileCards(ArrayList<CreditCard> creditCards,Account account) throws Exception { //μετατροπή απο Arraylist se json , κρυπτογράφηση και αποθηκευση
        Gson gson = new Gson();
        String json = gson.toJson(creditCards);
        File file = new File("users//"+account.getUsername()+"//cards");
        if(!file.exists())
            file.createNewFile();

        FileOutputStream fos = new FileOutputStream(file);
        byte[] encryptedcards =cryptoService.encryptWithAES(json.getBytes(),usersManager.getAES(usersManager.getCurrent()));
        fos.write(encryptedcards);
        fos.close();
    }
}
