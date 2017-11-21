package se.fransbernhard.delivery;

/**
 * Created by mimilundberg on 2017-11-21.
 */

class Client {
    private int clientID;
    private String clientName;
    private String contactPerson;
    private int contactNumber;
    private String contactEmail;
    private String clientAdress;
    private int clientZipCode;

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public void setContactNumber(int contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public void setClientAdress(String clientAdress) {
        this.clientAdress = clientAdress;
    }

    public void setClientZipCode(int clientZipCode) {
        this.clientZipCode = clientZipCode;
    }

    public void setClientCity(String clientCity) {
        this.clientCity = clientCity;
    }

    public int getClientID() {

        return clientID;
    }

    public String getClientName() {
        return clientName;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public int getContactNumber() {
        return contactNumber;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public String getClientAdress() {
        return clientAdress;
    }

    public int getClientZipCode() {
        return clientZipCode;
    }

    public String getClientCity() {
        return clientCity;
    }

    private String clientCity;
}
