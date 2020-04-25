package cardsaver.storage;

import cardsaver.auth.Account;
import cardsaver.frontend.CreditCard;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CardsManager {



    public ArrayList<CreditCard> getEncryptedCards(String username){

return null;



    }
    public void updateCards(ArrayList<CreditCard> creditCards,Account account) throws IOException {
        Gson gson = new Gson();
        String json = gson.toJson(creditCards);
        File file = new File("users//"+account.getUsername()+"cards");
        if(!file.exists())
            file.createNewFile();
        FileOutputStream fos = new FileOutputStream(file);

        byte[] encryptedcards = new byte[0];
            fos.write(encryptedcards);
            //fos.close(); There is no more need for this line since you had created the instance of "fos" inside the try. And this will automatically close the OutputStream


    }

    public CardsManager(){

    }
}
