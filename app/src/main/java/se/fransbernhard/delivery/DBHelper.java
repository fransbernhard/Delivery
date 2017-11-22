package se.fransbernhard.delivery;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mimilundberg on 2017-11-21.
 */

public class DBHelper extends SQLiteOpenHelper {
    public static final int DB_VERSION = 1;

    public DBHelper(Context context) {
        super(context, "Delivered", null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // When creating the database
        String sqlOrder = "CREATE TABLE Orders ( " +
                "orderID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "orderSum FLOAT NOT NULL," +
                "deliveryTime INTEGER," +
                "delivered INTEGER," +
                "fk_clientID INTEGER," +
                "FOREIGN KEY(fk_clientID) REFERENCES Client(clientID));";

        String sqlClient = "CREATE TABLE Clients ( " +
                "clientID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "clientName VARCHAR(25)," +
                "contactPerson VARCHAR(25)," +
                "contactNumber INTEGER," +
                "contactEmail VARCHAR(30)," +
                "clientAdress VARCHAR(25)," +
                "clientZipCode INTEGER," +
                "clientCity VARCHAR(20));";

        db.execSQL(sqlOrder);
        db.execSQL(sqlClient);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // When upgrading the database
    }
/*

    public List<Order> getAllOrders(){
        List<Order> orderList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.query("Orders",null, null, null,null,null,null);

        boolean success = c.moveToFirst();
        if(success) {
            do {
                Order order = new Order();

                order.orderID = c.getInt(0);
                order.orderSum = c.getInt(1);
                order.deliveryTime = c.getString(2);
                order.delivered = c.getInt(3);
                order.clientID = c.getInt(4);

                orderList.add(order);
                Log.d("SQL", order.orderID + "," + order.orderSum + "," + order.clientID);
            } while (c.moveToNext());
        }
        db.close();
        return orderList;
    }
    */

    public List<Client> getAllClients(){
        List<Client> clientList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.query("Clients",null, null, null,null,null,null);

        boolean success = c.moveToFirst();
        if(success) {
            do {
                Client client = new Client();

                client.setClientID(c.getInt(0));
                client.setClientName(c.getString(1));
                client.setContactPerson(c.getString(2));
                client.setContactNumber(c.getInt(3));
                client.setContactEmail(c.getString(4));
                client.setClientAdress(c.getString(5));
                client.setClientZipCode(c.getInt(6));
                client.setClientCity(c.getString(7));

                clientList.add(client);
            } while (c.moveToNext());
        }
        db.close();
        return clientList;
    }

    public List<Order> getAllDeliveryStatus(int delivered, int limit) {
        List<Order> orderList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        String selection = "delivered=?";
        String[] selectionArgs = new String[] {Integer.toString(delivered)};

        Cursor c = db.query("Orders", null, selection, selectionArgs, null, null, "deliveryTime ASC", Integer.toString(limit));

        boolean success = c.moveToFirst();
        if(success) {
            do {
                Order order = new Order();

                order.setOrderID(c.getInt(0));
                order.setOrderSum(c.getInt(1));
                order.setDeliveryTime(c.getString(2));
                order.setDelivered(c.getInt(3));
                order.setClientID(c.getInt(4));

                orderList.add(order);
            } while (c.moveToNext());
        }
        db.close();
        return orderList;
    }

    public List<Order> getAllDeliveryStatus(int delivered) {
        List<Order> orderList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        String selection = "delivered=?";
        String[] selectionArgs = new String[] {Integer.toString(delivered)};

        Cursor c = db.query("Orders", null, selection, selectionArgs, null, null, "deliveryTime ASC", null);

        boolean success = c.moveToFirst();
        if(success) {
            do {
                Order order = new Order();

                order.setOrderID(c.getInt(0));
                order.setOrderSum(c.getInt(1));
                order.setDeliveryTime(c.getString(2));
                order.setDelivered(c.getInt(3));
                order.setClientID(c.getInt(4));

                orderList.add(order);
            } while (c.moveToNext());
        }
        db.close();
        return orderList;
    }

    /**
     * Update delivered field in database
     * @params order - - object
     * @return true or false boolean
     */
    public boolean updateDelivered(Order order){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();
        int newStatus = order.delivered == 1 ? 0 : 1;

        cv.put("delivered", newStatus);

        String selection = "orderID=?";
        String[] selectionArgs = new String[]{Integer.toString(order.getOrderID())};

        int rows = db.update("Orders", cv, selection, selectionArgs);
        db.close();

        return  rows == 1;
    }

    /**
     * Get all Clients from the database
     * @param clientID - - where to start
     * @return a list of orders
     */
    public Client getClient(int clientID){
        SQLiteDatabase db = getReadableDatabase();

        String selection = "clientID=?";
        String[] selectionArgs = new String[]{Integer.toString(clientID)};

        Cursor c = db.query("Clients",null, selection, selectionArgs,null,null,null);
        c.moveToFirst();

        Client client = new Client();
        client.setClientID(c.getInt(0));
        client.setClientName(c.getString(1));
        client.setContactPerson(c.getString(2));
        client.setContactNumber(c.getInt(3));
        client.setContactEmail(c.getString(4));
        client.setClientAdress(c.getString(5));
        client.setClientZipCode(c.getInt(6));
        client.setClientCity(c.getString(7));

        db.close();
        return client;
    }


    public Order getOrder(int orderID){
        SQLiteDatabase db = getReadableDatabase();

        String selection = "orderID=?";
        String[] selectionArgs = new String[]{Integer.toString(orderID)};

        Cursor c = db.query("Orders",null, selection, selectionArgs,null,null,null);
        c.moveToFirst();

        Order order = new Order();
        order.setOrderID(c.getInt(0));
        order.setOrderSum(c.getInt(1));
        order.setDeliveryTime(c.getString(2));
        order.setDelivered(c.getInt(3));
        order.setClientID(c.getInt(4));

        db.close();
        return order;
    }

    public void addClients(String clientName, String contactPerson, int contactNumber, String contactEmail, String clientAdress, int clientZipCode, String clientCity){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("clientName",clientName);
        contentValues.put("contactPerson", contactPerson);
        contentValues.put("contactNumber", contactNumber);
        contentValues.put("contactEmail", contactEmail);
        contentValues.put("clientAdress", clientAdress);
        contentValues.put("clientZipCode", clientZipCode);
        contentValues.put("clientCity", clientCity);

        db.insert("Clients",null,contentValues);
        db.close();
    }

    public void addOrders(int orderSum, int deliveryTime, int fk_clientID){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("orderSum", orderSum);
        contentValues.put("deliveryTime", deliveryTime);
        contentValues.put("delivered", 0);
        contentValues.put("fk_clientID", fk_clientID);

        db.insert("Orders",null,contentValues);
        db.close();
    }

}



