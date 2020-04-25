package cardsaver.auth;

public class Account {
    String username;
    String firstname;
    String lastname;
    byte[] salt;
    byte[] hashedpassword;
    String email;
    String id;


    public Account(String username, String firstname, String lastname , String email, byte[] hashedpassword , String id ,byte[] salt) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.salt = salt;
        this.hashedpassword = hashedpassword;
        this.email = email;
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public byte[] getSalt() {
        return salt;
    }

    public byte[] getHashedpassword() {
        return hashedpassword;
    }

    public String getEmail() {
        return email;
    }

    public String getId() {
        return id;
    }
}
