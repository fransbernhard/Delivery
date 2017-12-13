package se.fransbernhard.delivery;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mimilundberg on 2017-11-21.
 */

public class DBHelper extends SQLiteOpenHelper {
    public static final int DB_VERSION = 6;
    private Context context;

    /**
     * DBHelper constructor
     * @param context - - the environment you are in
     */
    public DBHelper(Context context) {
        super(context, "Delivered", null, DB_VERSION);
        this.context = context;
    }

    /**
     * Override SQLiteOpenHelpers onCreate method
     * @param db - - the database
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // When creating the database
        try {
            insertFromFile(R.raw.database, db);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Override SQLiteOpenHelpers onUpgrade method
     * @param db - - the database
     * @param oldVersion - - old version
     * @param newVersion - - new version
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // When upgrading the database
        if(oldVersion < newVersion){
            db.delete("Orders", null, null);
            db.delete("Clients", null, null);
            onCreate(db);
        }
    }

    /**
     * Insert data from file
     * @param db - - the database
     * @param resourceId - - the resource ID
     * @return int with result
     */
    public int insertFromFile(int resourceId, SQLiteDatabase db) throws IOException {
        int result = 0;

        InputStream insertStream = context.getResources().openRawResource(resourceId);
        BufferedReader insertReader = new BufferedReader(new InputStreamReader(insertStream));

        String insertStatment = "";
        while (insertReader.ready()) {
            insertStatment += insertReader.readLine();
            result ++;
        }
        insertReader.close();
        String[] queries = insertStatment.split(";");
        for(String query : queries) {
            db.execSQL(query);
        }

        return result;
    }

    /**
     * @return a list of all clients
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
                client.setContactNumber(c.getString(3));
                client.setContactEmail(c.getString(4));
                client.setClientAdress(c.getString(5));
                client.setClientZipCode(c.getString(6));
                client.setClientCity(c.getString(7));
                client.setClientLat(c.getDouble(8));
                client.setClientLong(c.getDouble(9));

                clientList.add(client);
            } while (c.moveToNext());
        }
        db.close();
        return clientList;
    }

    /**
     * @return a limited list of orders with a specific delivery status
     * @param delivered - - delivery status
     * @param limit - - how many order items that should be returned
     */
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

    /**
     * @return a list of orders with a specific delivery status
     * @param delivered - - delivery status
     */
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
     * @param order - - object
     * @return true or false boolean
     */
    public boolean updateDelivered(Order order){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();
        int newStatus = order.getDelivered() == 1 ? 0 : 1;

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
        client.setContactNumber(c.getString(3));
        client.setContactEmail(c.getString(4));
        client.setClientAdress(c.getString(5));
        client.setClientZipCode(c.getString(6));
        client.setClientCity(c.getString(7));
        client.setClientLat(c.getDouble(8));
        client.setClientLong(c.getDouble(9));

        db.close();
        return client;
    }

    /**
     * @param orderID - - orders id
     * @return a order at a specific position
     */
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

}



