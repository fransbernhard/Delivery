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
