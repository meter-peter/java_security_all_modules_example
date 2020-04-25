package cardsaver.auth;

import cardsaver.Controller;
import cardsaver.crypto.CryptoService;
import cardsaver.storage.UsersManager;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;
import java.util.Base64;

public class AuthService {
    Controller controller;
    CryptoService cryptoService ;
    List<Account> accounts;
    Account currentaccount;
    UsersManager usersManager;

    public AuthService(Controller controller) throws NoSuchAlgorithmException, IOException, InvalidKeySpecException {
        this.controller=controller;
        accounts= new ArrayList<>();
        cryptoService = new CryptoService();
        usersManager = new UsersManager();
    }


    public Account authorizeApp(Account account){
        return account;
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

    public AuthError loginAccount(String username , String password) throws Exception {
        for (Account tobesearched : accounts) {
            if (tobesearched.getUsername().equals(username)) {
                byte[] decryptedHash = cryptoService.decryptPassword(tobesearched.getHashedpassword());

                String base64decryptedhash = Base64.getEncoder().encodeToString(decryptedHash);
                String userinputhashtobase64 = Base64.getEncoder().encodeToString(cryptoService.generateSaltedHash(password,tobesearched.getSalt()));

                if(base64decryptedhash.equals(userinputhashtobase64)){
                    System.out.println("SUCCESS");
                    return AuthError.SUCCESS;
                }

                break;
            }else {
                return AuthError.NOT_FOUND;
            }
        }
        return null;
    }
    public void createAccount(String username , String firstname , String lastname , String email , String password) throws Exception {
        if(!containsName(accounts,username)){
            byte[] salt = generateSalt();

            byte[] saltedhash = cryptoService.generateSaltedHash(password,salt);

            byte[] encryptedsaltedhash = cryptoService.encryptSaltedHash(saltedhash);

            Account tobecreated = new Account(username ,
                    firstname ,
                    lastname ,
                    email ,
                    encryptedsaltedhash,
                    "ID",
                    salt);

            accounts.add(tobecreated);
            System.out.println(accounts.get(0).hashedpassword.toString());
            usersManager.updateUsers(accounts);
        }



    }
}
