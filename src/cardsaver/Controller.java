package cardsaver;

import cardsaver.auth.Account;
import cardsaver.auth.AuthService;
import cardsaver.crypto.CryptoService;
import cardsaver.crypto.IntegrityChecker;
import cardsaver.frontend.FrontendManager;
import cardsaver.storage.CardsManager;
import cardsaver.storage.UsersManager;

import java.io.File;

public class Controller { //κλάση που ελέγχει τη ροή δεδομένων
    CardsManager cardsManager;
    AuthService authService;
    CryptoService cryptoService;
    FrontendManager frontendManager;
    Account currentUser;
    UsersManager usersManager;
    IntegrityChecker integrityChecker;

    public Controller() throws Exception {
        cryptoService = new CryptoService();
        usersManager = new UsersManager(cryptoService);
        authService = new AuthService(this,usersManager);
        cardsManager = new CardsManager(cryptoService,usersManager);
        frontendManager = new FrontendManager(this,authService,cardsManager,cryptoService,usersManager);
        integrityChecker = new IntegrityChecker(cryptoService,usersManager,cardsManager);

    }
    public void continuewithregister(Account account) throws Exception {
        currentUser = account;
        usersManager.setCurrent(account);
        usersManager.createUserDrectory(currentUser);
        usersManager.saveAES(cryptoService.generateAES(),currentUser);
        frontendManager.onauthorize();

    }
 public void continuewithlogin(Account account) throws Exception {
        currentUser= account;
        usersManager.setCurrent(account);
        frontendManager.onauthorize();


    }

    public boolean checkintegrity() throws Exception {
       File file = new File("users//"+authService.getCurrentaccount().getUsername()+"//encryptedsig.cryptSig");
       if(file.exists()){
           return   integrityChecker.checkintegrity();
       }else return false;

    }
 public void finalmethod() throws Exception {

     integrityChecker.securefiles();
    }



}
