package cardsaver;

import cardsaver.auth.AuthService;
import cardsaver.crypto.CryptoService;
import cardsaver.frontend.Frontend;
import cardsaver.storage.FileManager;

public class Controller {
    AuthService authService;
    FileManager fileManager;
    Frontend frontend;
    CryptoService cryptoService;

    public Controller(AuthService authService, FileManager fileManager, Frontend frontend, CryptoService cryptoService) {
        this.authService = authService;
        this.fileManager = fileManager;
        this.frontend = frontend;
        this.cryptoService = cryptoService;
    }
}
