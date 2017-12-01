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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

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
    private Button deliverd;
    private Button unDeliverd;
    private TextView deliverdText;
    private TextView unDeliverdText;
    private MenuItem refreshButton;
    private Animation animation;

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

        ordersDelivered = dbhelper.getAllDeliveryStatus(1);
        ordersNotDelivered = dbhelper.getAllDeliveryStatus(0, amountOfOrders);
        clients = dbhelper.getAllClients();

        deliverd = findViewById(R.id.LevButton);
//        deliverdText = findViewById(R.id.LevText);
        unDeliverd = findViewById(R.id.EjLevButton);
//        unDeliverdText = findViewById(R.id.EjLevText);

        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink_effect);


        setAdapter();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_list_toolbar, menu);
        refreshButton = menu.findItem(R.id.refreshButton);
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
             //   intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                if (showingNotDelivered)
                    intent.putExtra("ORDER_ID", ordersNotDelivered.get(position).getOrderID());
                else
                    intent.putExtra("ORDER_ID", ordersDelivered.get(position).getOrderID());
                startActivity(intent);
            }
        });
    }

    public void clickedNotDelivered(View v) {
        showingNotDelivered = true;
        refreshButton.setVisible(true);

//        unDeliverd.setVisibility(View.VISIBLE);
//        unDeliverdText.setVisibility(View.INVISIBLE);
//        deliverd.setVisibility(View.INVISIBLE);
//        deliverdText.setVisibility(View.VISIBLE);

        setAdapter();

        unDeliverd.setBackgroundColor(getResources().getColor(R.color.colorDelivered));
        unDeliverd.setTextColor(getResources().getColor(R.color.colorNotDelivered));
        deliverd.setBackgroundColor(getResources().getColor(R.color.colorNotDelivered));
        deliverd.setTextColor(getResources().getColor(R.color.colorButton));

        unDeliverd.startAnimation(animation);
    }

    public void clickedDelivered(View v) {
        showingNotDelivered = false;
        refreshButton.setVisible(false);

//        deliverd.setVisibility(View.VISIBLE);
//        deliverdText.setVisibility(View.INVISIBLE);
//        unDeliverd.setVisibility(View.INVISIBLE);
//        unDeliverdText.setVisibility(View.VISIBLE);

        deliverd.setBackgroundColor(getResources().getColor(R.color.colorDelivered));
        deliverd.setTextColor(getResources().getColor(R.color.colorNotDelivered));
        unDeliverd.setBackgroundColor(getResources().getColor(R.color.colorNotDelivered));
        unDeliverd.setTextColor(getResources().getColor(R.color.colorButton));

        deliverd.startAnimation(animation);

        setAdapter();
    }

    //    public void mapClick(View v){
//        // Create a Uri from an intent string. Use the result to create an Intent.
//        Uri gmmIntentUri = Uri.parse("google.streetview:cbll=" + latlong);
//        // Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
//        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
//        // Make the Intent explicit by setting the Google Maps package
//        mapIntent.setPackage("com.google.android.apps.maps");
//
//        // Attempt to start an activity that can handle the Intent
//        startActivity(mapIntent);
//    }


}

