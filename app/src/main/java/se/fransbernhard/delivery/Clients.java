package se.fransbernhard.delivery;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

public class Clients {
    public String name;
    public String adress;
    public String deliveryDate;
    public String imageUrl;

    public static ArrayList<Clients> getKunderFromFile(String filename, Context context){
        final ArrayList<Clients> kundLista = new ArrayList<>();

        try {
            String jsonString = loadJsonFromAsset("kunder.json", context);
            JSONObject json = new JSONObject(jsonString);
            JSONArray kunder = json.getJSONArray("Kunder");

            for (int i = 0; i < kunder.length(); i++){
                Clients kund = new Clients();

                kund.name = kunder.getJSONObject(i).getString("name");
                kund.adress = kunder.getJSONObject(i).getString("adress");
                kund.deliveryDate = kunder.getJSONObject(i).getString("deliveryDate");
                kund.imageUrl = kunder.getJSONObject(i).getString("image");

                kundLista.add(kund);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return kundLista;
    }

    private static String loadJsonFromAsset(String filename, Context context) {
        String json = null;

        try {
            InputStream is = context.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        }
        catch (java.io.IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return json;

    }
}
