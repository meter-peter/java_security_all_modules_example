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

    public AuthService(Controller controller, UsersManager usersManager) throws NoSuchAlgorithmException, IOException, InvalidKeySpecException {
        this.controller=controller;

        cryptoService = new CryptoService();
        this.usersManager = usersManager;

        if(usersManager.getUsers()!=null)
        accounts = usersManager.getUsers();
        else {accounts = new ArrayList<>();}
        System.out.println(accounts);
    }

    public Account getCurrentaccount() {
        return currentaccount;
    }
    UsersManager usersManager;

    private boolean containsName(List<Account> list,String name) { //ελεγχος για το αν υπαρχει εγγεγραμένος χρήστης
        try {
            return list.stream().filter(o -> o.getUsername().equals(name)).findFirst().isPresent();
        } catch (NullPointerException e){}
        return false;
    }

    public byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[32];
        random.nextBytes(bytes);
        return bytes;
    }

    public AuthStatus loginAccount(String username , String password) throws Exception {
        boolean flag = false;
        for (Account tobesearched : accounts) { //ελεγχουμε ολη τη λιστα με τους λογαριασμούς
            if (tobesearched.getUsername().toLowerCase().equals(username.toLowerCase())) { //αν βρουμε το username συγκρίνουμε τα base64 των hashes
                byte[] decryptedHash = cryptoService.decryptWithRSA(tobesearched.getHashedpassword());

                String base64decryptedhash = Base64.getEncoder().encodeToString(decryptedHash);
                String userinputhashtobase64 = Base64.getEncoder().encodeToString(cryptoService.generateSaltedHash(password,tobesearched.getSalt()));

                if(base64decryptedhash.equals(userinputhashtobase64)){
                    System.out.println("SUCCESS");
                    currentaccount= tobesearched;
                    controller.continuewithlogin(tobesearched);

                    flag=true;
                    return AuthStatus.SUCCESS;
                }else{
                    java.util.concurrent.TimeUnit.SECONDS.sleep(2); //timeout se periptwsh pou to exei lathos gia na kathysterhsoume se periptwsh bruteforce attack;
                    return AuthStatus.WRONG_PASS;
                }

            }

        }
        if(flag=false)
            return AuthStatus.NOT_FOUND;
        else return null;
    }
    public AuthStatus createAccount(String username , String firstname , String lastname , String email , String password) throws Exception {
        if(!containsName(accounts,username)){ //αν δεν υπάρχει ο λογαριασμός μπορεί να δημιουργηθεί
            byte[] salt = generateSalt();

            byte[] saltedhash = cryptoService.generateSaltedHash(password,salt);

            byte[] encryptedsaltedhash = cryptoService.encryptWithRSA(saltedhash);

            Account tobecreated = new Account(username ,
                    firstname ,
                    lastname ,
                    email ,
                    encryptedsaltedhash,
                    "ID",
                    salt);
            System.out.println(tobecreated.toString());

            //αρχικοποιήσεις μετα γην εγγραφή
            accounts.add(tobecreated);

            currentaccount = tobecreated;
            System.out.println(accounts.get(0).hashedpassword.toString());
            usersManager.updateUsers(accounts);
            controller.continuewithregister(tobecreated);
            usersManager.saveAES(cryptoService.generateAES(),tobecreated);
            //controller.checkintegrity();
            return AuthStatus.SUCCESS;
        }
        return AuthStatus.DUPLICATE_USER;


    }
}
