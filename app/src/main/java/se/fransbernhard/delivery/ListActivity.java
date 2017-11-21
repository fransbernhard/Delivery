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
    private DBHelper dbHelper;
    private SharedPreferences shared;
    private int amountOfOrders;
    private List<Order> ordersDelivered, ordersNotDelivered;
    private List<Client> clients;

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
        dbHelper = new DBHelper(this);
        amountOfOrders = shared.getInt("NUMBER_OF_ORDERS", 10);
        ordersDelivered = dbHelper.getAllDeliveryStatus(1, amountOfOrders);
        ordersNotDelivered = dbHelper.getAllDeliveryStatus(0, amountOfOrders);
        clients = dbHelper.getAllClients();


     //   final ArrayList<Clients> kundLista = Clients.getKunderFromFile("kunder.json", this);
        final ClientsAdapter adapter = new ClientsAdapter(this, clients, ordersNotDelivered);
        myList.setAdapter(adapter);
        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListActivity.this, DetailActivity.class);
                startActivity(intent);
              }
          }
        );
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
        }
        return super.onOptionsItemSelected(item);
    }

}

