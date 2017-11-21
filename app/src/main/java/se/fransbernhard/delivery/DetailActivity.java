package se.fransbernhard.delivery;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DBHelper dbHelper;
    private Order order;
    private Client client;
    private TextView clientName, clientID, deliveryDate, orderID,
                    orderSum, contactPerson, contactNumber, email, address, zipCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        toolbar = (Toolbar) findViewById(R.id.myToolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        // This will remove App name
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
        dbHelper = new DBHelper(this);
        Intent intent = getIntent();
        order = dbHelper.getOrder(intent.getIntExtra("ORDER_ID", 1));
        client = dbHelper.getClient(order.getClientID());

        clientName = (TextView)findViewById(R.id.clientName);
        clientID = (TextView)findViewById(R.id.clientID);
        deliveryDate = (TextView)findViewById(R.id.deliveryDate);
        orderID = (TextView)findViewById(R.id.orderID);
        orderSum = (TextView)findViewById(R.id.orderSum);
        contactPerson = (TextView)findViewById(R.id.contactPerson);
        contactNumber = (TextView)findViewById(R.id.contactNumber);
        email = (TextView)findViewById(R.id.email);
        address = (TextView)findViewById(R.id.address);
        zipCode = (TextView)findViewById(R.id.zipCode);

        clientName.setText("Clientname:" + client.getClientName());
        clientID.setText("ClientID: " + Integer.toString(client.getClientID()));
        deliveryDate.setText("Delivery date: " + order.getDeliveryTime());
        orderID.setText("Order ID: " + Integer.toString(order.getOrderID()));
        orderSum.setText("Ordersum: " + Integer.toString(order.getOrderSum()) + " kr");
        contactPerson.setText("Contactperson: " + client.getContactPerson());
        contactNumber.setText("Contactnumber: " + Integer.toString(client.getContactNumber()));
        email.setText("Email: " + client.getContactEmail());
        address.setText("Adress: " + client.getClientAdress());
        zipCode.setText("Zip and City: " + Integer.toString(client.getClientZipCode()) + " " + client.getClientCity());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_detail_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settingsButton:
                startActivity(new Intent(this, PreferenceActivity.class));
                return true;
            case R.id.listButton:
                startActivity(new Intent(this, ListActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
