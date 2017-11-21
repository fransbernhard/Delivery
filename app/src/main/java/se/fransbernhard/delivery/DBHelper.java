package se.fransbernhard.delivery;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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

    /**
     * Get all Orders from the database
     * @param orderID - - where to start
     * @param amount - - how many orders to get
     * @return a list of orders
     */
    public List<Order> getLimitedOrders(int orderID, int amount){
        List<Order> orderList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        String selection = "orderID>=?";
        String[] selectionArgs = new String[]{Integer.toString(orderID)};

        Cursor c = db.query("Orders",null, selection, selectionArgs,null,null,null);

        boolean success = c.moveToFirst();
        if(success) {
            for (int i = 0; i < amount; i++) {
                Order order = new Order();

                order.orderID = c.getInt(0);
                order.orderSum = c.getInt(1);
                order.deliveryTime = c.getString(2);
                order.delivered = c.getInt(3);
                order.clientID = c.getInt(4);

                orderList.add(order);
                Log.d("SQL", order.orderID + "," + order.orderSum + "," + order.clientID);
                if(!c.moveToNext()){
                    break;
                }
            }
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
        String[] selectionArgs = new String[]{Integer.toString(order.orderID)};

        int rows = db.update("Orders", cv, selection, selectionArgs);
        db.close();

        return  rows == 1;
    }

    /**
     * Get all Clients from the database
     * @param index - - where to start
     * @return a list of orders
     */
    public Client getClient(int index){
        SQLiteDatabase db = getReadableDatabase();

        String selection = "clientID=?";
        String[] selectionArgs = new String[]{Integer.toString(index)};

        Cursor c = db.query("Clients",null, selection, selectionArgs,null,null,null);

        Client client = new Client();
        client.clientID = c.getInt(0);
        client.clientName = c.getString(1);
        client.contactPerson = c.getString(2);
        client.contactNumber = c.getInt(3);
        client.contactEmail = c.getString(4);
        client.clientAdress = c.getString(5);
        client.clientZipCode = c.getInt(6);
        client.clientCity = c.getString(7);

        db.close();
        return client;
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



