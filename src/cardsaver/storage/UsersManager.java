package cardsaver.storage;

import cardsaver.auth.Account;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class UsersManager {

    File users;

public UsersManager() throws IOException {
    users = new File("users.json");
    if(!users.exists())
        users.createNewFile();
    new File("users").mkdir();

}

    public ArrayList<Account> getUsers(){
        try {
            Reader reader = Files.newBufferedReader(Paths.get(users.getPath()));

            Type listType = new TypeToken<ArrayList<Account>>() {}.getType();
            return new Gson().fromJson(reader, listType);
        } catch (IOException e) {e.printStackTrace();

        }
        ArrayList<Account> temp = new ArrayList<>();
        return temp;
    }

    public void updateUsers(List<Account> appusers){
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

    public void saveAES(byte[] encrypteddata ,Account account) throws IOException {
        File key = new File("users//"+account.getUsername()+"//encryptedData.dat");
        key.createNewFile();
        try (FileOutputStream fos = new FileOutputStream(key)) {
            fos.write(encrypteddata);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public byte[] getAES(){

    return null;
    }
}
