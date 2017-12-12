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
    private String clientCity;

    /**
     * Set client id
     * @param clientID contains a int with clients id (Ex: 2)
     */
    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    /**
     * Set client name
     * @param clientName contains a String with clients name (Ex: "Jean")
     */
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    /**
     * Set contact persons name
     * @param contactPerson contains a String with contact persons name (Ex: "Jean")
     */
    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    /**
     * Set contact persons number
     * @param contactNumber contains a String with contact persons number (Ex: "0745456638")
     */
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    /**
     * Set contact persons email
     * @param contactEmail contains a String with contact persons email (Ex: "markus@gmail.com")
     */
    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    /**
     * Set client adress
     * @param clientAdress contains a String with clients adress (Ex: "Nybergsgatan 6B")
     */
    public void setClientAdress(String clientAdress) {
        this.clientAdress = clientAdress;
    }

    /**
     * Set client zip code
     * @param clientZipCode contains a String with clients zip code (Ex: "114 45")
     */
    public void setClientZipCode(String clientZipCode) {
        this.clientZipCode = clientZipCode;
    }

    /**
     * Set client city
     * @param clientCity contains a String with clients city (Ex: "Stockholm")
     */
    public void setClientCity(String clientCity) {
        this.clientCity = clientCity;
    }

    /**
     * Set client latitude
     * @param clientLat a double with the clients latitude (Ex: 9.374738239)
     */
    public void setClientLat(double clientLat) {
        this.clientLat = clientLat;
    }

    /**
     * Set client longitude
     * @param clientLong a double with the clients longitude (Ex: 2.374999239)
     */
    public void setClientLong(double clientLong) {
        this.clientLong = clientLong;
    }

    /**
     * @return a integer with the clients id (Ex: 14)
     */
    public int getClientID() {
        return clientID;
    }

    /**
     * @return a String with the clients name (Ex: "Mimi Larsson")
     */
    public String getClientName() {
        return clientName;
    }

    /**
     * @return a String with the contact persons name (Ex: "Malcolm Svensson")
     */
    public String getContactPerson() {
        return contactPerson;
    }

    /**
     * @return a String with the contact persons number (Ex: "0789354635")
     */
    public String getContactNumber() {
        return contactNumber;
    }

    /**
     * @return a String with the contact persons mail (Ex: "contact@mail.com")
     */
    public String getContactEmail() {
        return contactEmail;
    }

    /**
     * @return a String with clients address (Ex: "Nybergsgatan 6B")
     */
    public String getClientAdress() {
        return clientAdress;
    }

    /**
     * @return a String with the clients address (Ex: "Nybergsgatan 6B")
     */
    public String getClientZipCode() {
        return clientZipCode;
    }

    /**
     * @return a String with the clients city (Ex: "Norrköping")
     */
    public String getClientCity() {
        return clientCity;
    }

    /**
     * @return a double with the clients latitude (Ex: 2.374999239)
     */
    public double getClientLat() {
        return clientLat;
    }

    /**
     * @return a double with the clients longitude (Ex: 1.374999239)
     */
    public double getClientLong() {
        return clientLong;
    }

}
