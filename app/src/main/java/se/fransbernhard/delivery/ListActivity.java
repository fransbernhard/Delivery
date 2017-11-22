package se.fransbernhard.delivery;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

public class ListActivity extends AppCompatActivity {

    private ListView myList;
    private Toolbar toolbar;
    private DBHelper dbhelper;
    private SharedPreferences shared;
    private int amountOfOrders;
    private List<Order> ordersDelivered, ordersNotDelivered;
    private List<Client> clients;
    private boolean showingNotDelivered;
    private ClientsAdapter adapterNotDelivered, adapterDelivered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        myList = (ListView)findViewById(R.id.OrderLista);
        toolbar = (Toolbar) findViewById(R.id.myToolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        // This will remove App name
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
        shared = getSharedPreferences("PREFERENCES",MODE_PRIVATE);
        dbhelper = new DBHelper(this);

        amountOfOrders = shared.getInt("CURRENT_NUMBER_OF_ORDERS", shared.getInt("NUMBER_OF_ORDERS", 10));
        showingNotDelivered = true;

        dbhelper.addClients("IT Högskolan", "Markus kontakt", 0734526717, "markus@me.se", "markus vägen 13", 23412, "malmö");
        dbhelper.addClients("TicTale", "Mimi kontakt", 0734526717, "mimi@me.se", "mimi vägen 14", 23412, "sthlm");
        dbhelper.addClients("Wordpress", "Jean kontakt", 0734526717, "jean@me.se", "jean vägen 15", 23412, "göteborg");
        dbhelper.addClients("Spotify", "August kontakt", 0734526717, "august@me.se", "august vägen 13", 23412, "lund");
        dbhelper.addClients("Apple", "Ria kontakt", 0734526717, "ria@me.se", "ria vägen 13", 23412, "österlen");
        dbhelper.addClients("proust", "Mimi kontakt", 0734526717, "mimi@me.se", "södra vägen 13", 23412, "sthlm");
        dbhelper.addClients("August", "Mimi kontakt", 0734526717, "mimi@me.se", "södra vägen 13", 23412, "sthlm");
        dbhelper.addClients("August", "Mimi kontakt", 0734526717, "mimi@me.se", "södra vägen 13", 23412, "sthlm");
        dbhelper.addClients("August", "Mimi kontakt", 0734526717, "mimi@me.se", "södra vägen 13", 23412, "sthlm");
        dbhelper.addClients("August", "Mimi kontakt", 0734526717, "mimi@me.se", "södra vägen 13", 23412, "sthlm");
        dbhelper.addClients("August", "Mimi kontakt", 0734526717, "mimi@me.se", "södra vägen 13", 23412, "sthlm");
        dbhelper.addClients("August", "Mimi kontakt", 0734526717, "mimi@me.se", "södra vägen 13", 23412, "sthlm");
        dbhelper.addClients("August", "Mimi kontakt", 0734526717, "mimi@me.se", "södra vägen 13", 23412, "sthlm");
        dbhelper.addClients("August", "Mimi kontakt", 0734526717, "mimi@me.se", "södra vägen 13", 23412, "sthlm");
        dbhelper.addClients("August", "Mimi kontakt", 0734526717, "mimi@me.se", "södra vägen 13", 23412, "sthlm");
        dbhelper.addClients("August", "Mimi kontakt", 0734526717, "mimi@me.se", "södra vägen 13", 23412, "sthlm");
        dbhelper.addOrders(2345, 170304, 10);
        dbhelper.addOrders(3333, 170201, 7);
        dbhelper.addOrders(2333, 160807, 3);
        dbhelper.addOrders(2333, 171201, 6);
        dbhelper.addOrders(2333, 170601, 5);
        dbhelper.addOrders(2345, 170304, 6);
        dbhelper.addOrders(3333, 170404, 7);
        dbhelper.addOrders(2333, 170808, 5);
        dbhelper.addOrders(2333, 170909, 3);
        dbhelper.addOrders(2333, 170606, 7);
        dbhelper.addOrders(3333, 170505, 4);
        dbhelper.addOrders(2333, 170404, 3);
        dbhelper.addOrders(2333, 170303, 6);
        dbhelper.addOrders(2333, 170202, 2);

        ordersDelivered = dbhelper.getAllDeliveryStatus(1);
        ordersNotDelivered = dbhelper.getAllDeliveryStatus(0, amountOfOrders);
        clients = dbhelper.getAllClients();

        setAdapter();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_list_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settingsButton:
                Intent intent = new Intent(this, PreferenceActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
            case R.id.refreshButton:
                // TODO: 2017-11-20 (JEAN) Plocka hem nya ordrar från databasen
                refreshList();
        }
        return super.onOptionsItemSelected(item);
    }

    private void refreshList() {
        amountOfOrders = shared.getInt("NUMBER_OF_ORDERS", 10);
        SharedPreferences.Editor editor = shared.edit();
        editor.putInt("CURRENT_NUMBER_OF_ORDERS", amountOfOrders);
        editor.commit();
        ordersNotDelivered = dbhelper.getAllDeliveryStatus(0, amountOfOrders);
        setAdapter();
    }

    private void setAdapter() {
        adapterNotDelivered = new ClientsAdapter(this, clients, ordersNotDelivered);
        adapterDelivered = new ClientsAdapter(this, clients, ordersDelivered);

        if (showingNotDelivered)
            myList.setAdapter(adapterNotDelivered);
        else
            myList.setAdapter(adapterDelivered);

        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListActivity.this, DetailActivity.class);
                intent.putExtra("ORDER_ID", ordersNotDelivered.get(position).getOrderID());
                startActivity(intent);
            }
        });
    }

    public void clickedNotDelivered(View v) {
        showingNotDelivered = true;
        setAdapter();
    }

    public void clickedDelivered(View v) {
        showingNotDelivered = false;
        setAdapter();
    }

}

