package se.fransbernhard.delivery;

/**
 * Created by mimilundberg on 2017-11-21.
 */

class Order {
    private int orderID;
    private int orderSum;
    private String deliveryTime;
    private int delivered;

    public int getOrderID() {
        return orderID;
    }

    public int getOrderSum() {
        return orderSum;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public int getDelivered() {
        return delivered;
    }

    public int getClientID() {
        return clientID;
    }

    private int clientID;
}
