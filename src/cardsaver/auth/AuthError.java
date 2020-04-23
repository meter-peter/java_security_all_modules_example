package cardsaver.auth;

import java.util.List;

public enum AuthError {
    DATABASE(1, "A database error has occured."),
    DUPLICATE_USER(5007, "This user already exists.");
    //... add more cases here ...

    private final int id;
    private final String message;

    AuthError(int id, String message) {
        this.id = id;
        this.message = message;
    }

    public int getId() { return id; }
    public String getMessage() { return message; }




}
