package cardsaver.storage;

import cardsaver.auth.Account;
import com.google.gson.Gson;

import java.io.File;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class UsersManager {

public UsersManager(){

}



    public void updateUsers(List<Account> appusers){
        try {
            Writer writer = Files.newBufferedWriter(Paths.get("users.json"));
            Gson gson = new Gson();
            gson.toJson(appusers, writer);
            writer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }
}
