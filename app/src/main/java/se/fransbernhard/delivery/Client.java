package se.fransbernhard.delivery;

/**
 * Created by mimilundberg on 2017-11-21.
 */

class Client {
    private int clientID;
    private String clientName;
    private String contactPerson;
    private String contactNumber;
    private String contactEmail;
    private String clientAdress;
    private String clientZipCode;
    private double clientLat;
    private double clientLong;

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public void setClientAdress(String clientAdress) {
        this.clientAdress = clientAdress;
    }

    public void setClientZipCode(String clientZipCode) {
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

    public String getContactNumber() {
        return contactNumber;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public String getClientAdress() {
        return clientAdress;
    }

    public String getClientZipCode() {
        return clientZipCode;
    }

    public String getClientCity() {
        return clientCity;
    }

    public void setClientLat(double clientLat) {
        this.clientLat = clientLat;
    }

    public void setClientLong(double clientLong) {
        this.clientLong = clientLong;
    }

    public double getClientLat() {

        return clientLat;
    }

    public double getClientLong() {
        return clientLong;
    }

    private String clientCity;
}
