package cardsaver.auth;

import java.util.Objects;

public class Account {
    String username;
    String encryptedPassword;
    String email;
    String salt;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }


    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getEncryptedPassword(), getEmail(), getSalt());
    }
}
