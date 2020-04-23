package cardsaver.auth;

import java.util.ArrayList;
import java.util.List;

public class AuthService {
    List<Account> accounts;

    public AuthService() {
        accounts= new ArrayList<>();
    }

    public Account createnewaccount(String username , String firstname , String lastname , String email , String password){
    return null;
    }


    public List<Account> getAccounts() {
        return accounts;
    }
}
