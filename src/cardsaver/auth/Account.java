package cardsaver.auth;

import com.google.gson.*;

import java.util.List;

public class Account {
    String username;
    String firstname;
    String lastname;
    byte[] salt;
    String encryptedPassword;
    String email;
    String id;


    public Account(String username, String firstname, String lastname , String email, String id ,byte[] salt) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.salt = salt;
        this.encryptedPassword = encryptedPassword;
        this.email = email;
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public static ExclusionStrategy createStrategy(List<String> fields) {
        return new ExclusionStrategy() {
            public boolean shouldSkipField(FieldAttributes fieldAttributes) {
                if (fields.stream().anyMatch(e -> e.equals(fieldAttributes.getName()))) {
                    return false;
                }
                return true;
            }
            public boolean shouldSkipClass(Class aClass) {
                return false;
            }
        };
    }
}
