package cardsaver.auth;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class AuthService {
    List<Account> accounts;
    Account currentaccount;

    public AuthService() {
        accounts= new ArrayList<>();
    }

    public AuthError createnewaccount(String username , String firstname , String lastname , String email , String password) {
        if (containsName(accounts, username)) {
            return AuthError.DUPLICATE_USER;
        } else {
            return null;

        }
    }


    public List<Account> getAccounts() {
        return accounts;
    }

    private boolean containsName(List<Account> list,String name){
        return list.stream().filter(o -> o.getUsername().equals(name)).findFirst().isPresent();
    }

    public byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[32];
        random.nextBytes(bytes);
        return bytes;
    }

    public


    public void register(String username , String firstname , String lastname , String email , String password){
        if(!containsName(accounts,username)){
            Account tobecreated = new Account(username , firstname ,lastname , email , password ,"ID",);
            accounts.add()
        }



    }
}
