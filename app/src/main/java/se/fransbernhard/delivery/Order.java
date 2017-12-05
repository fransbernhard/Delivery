package se.fransbernhard.delivery;

/**
 * Created by mimilundberg on 2017-11-21.
 */

class Order {
    private int orderID;
    private int orderSum;
    private String deliveryTime;
    public int delivered;

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

    public int getOrderID() {

        return orderID;
    }

    public int getOrderSum() {
        return orderSum;
    }

    public String getDeliveryTime() {
        String formatted = deliveryTime.substring(4)+"/"+deliveryTime.substring(3,5)+"-"+"20"+deliveryTime.substring(0,2);
        return formatted;
    }

    public int getDelivered() {
        return delivered;
    }

    public int getClientID() {
        return clientID;
    }

    private int clientID;
}
