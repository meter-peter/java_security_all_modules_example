package cardsaver.frontend;



public class CreditCard {
    String Number;
    String Owner;
    String cvv;
    String type;
    String expirationDate;

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public String getOwner() {
        return Owner;
    }

    public void setOwner(String owner) {
        Owner = owner;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public CreditCard(String number, String owner, String cvv, String type ,String  expirationDate) {
        Number = number;
        Owner = owner;
        this.cvv = cvv;
        this.type = type;
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString(){
        String newString =getType()+": *"+ getNumber().substring(getNumber().length()-3);
        return newString;

    }
}
