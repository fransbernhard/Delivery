package se.fransbernhard.delivery;

/**
 * Created by mimilundberg on 2017-11-21.
 */

class Order {
    private int orderID;
    private int orderSum;
    private String deliveryTime;
    private int delivered;
    private int clientID;

    /**
     * Set Order sum
     * @param orderID contains a int with order id (Ex: 2)
     */
    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    /**
     * Set Order sum
     * @param orderSum contains a int with order sum (Ex: 2223)
     */
    public void setOrderSum(int orderSum) {
        this.orderSum = orderSum;
    }

    /**
     * Set Delivery time
     * @param deliveryTime contains a String with delivery time (Ex: "230417")
     */
    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    /**
     * Set Delivery status
     * @param delivered contains delivery status (Ex: 1 (true))
     */
    public void setDelivered(int delivered) {
        this.delivered = delivered;
    }

    /**
     * Set Client ID
     * @param clientID contains the clients ID (Ex: 2)
     */
    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    /**
     * @return a integer with the orders ID (Ex: 23)
     */
    public int getOrderID() {
        return orderID;
    }

    /**
     * @return a integer with the orders sum (Ex: 1200)
     */
    public int getOrderSum() {
        return orderSum;
    }

    /**
     * @return a String with the delivery time (Ex: "23/4-2017")
     */
    public String getDeliveryTime() {
        String formatted = deliveryTime.substring(4)+"/"+deliveryTime.substring(2,4)+"-"+"20"+deliveryTime.substring(0,2);
        return formatted;
    }

    /**
     * @return a integer with the orders delivery status (Ex: 1 = true)
     */
    public int getDelivered() {
        return delivered;
    }

    /**
     * @return a integer with the client ID (Ex: 2)
     */
    public int getClientID() {
        return clientID;
    }

}
