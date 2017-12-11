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


    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public void setOrderSum(int orderSum) {
        this.orderSum = orderSum;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public void setDelivered(int delivered) {
        this.delivered = delivered;
    }

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
