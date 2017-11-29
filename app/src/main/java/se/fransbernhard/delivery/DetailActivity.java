package se.fransbernhard.delivery;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

public class DetailActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private SharedPreferences shared;
    private DBHelper dbHelper;
    private Order order;
    private Client client;
    private TextView clientName, clientID, deliveryDate, orderID,
                    orderSum, contactPerson, contactNumber, email, address, zipCode;
    private ToggleButton deliveredToggleBtn;
    private int initialOrderStatus;
    private SMSHelper smsHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        toolbar = (Toolbar) findViewById(R.id.myToolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        // This will remove App name
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
        shared = getSharedPreferences("PREFERENCES",MODE_PRIVATE);
        dbHelper = new DBHelper(this);
        Intent intent = getIntent();
        order = dbHelper.getOrder(intent.getIntExtra("ORDER_ID", 1));
        client = dbHelper.getClient(order.getClientID());
        deliveredToggleBtn = (ToggleButton)findViewById(R.id.DeliveredToggleBtn);
        initialOrderStatus = order.delivered;
        deliveredToggleBtn.setChecked(initialOrderStatus==0);
        smsHelper = new SMSHelper(order.getOrderID());

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

        clientName.setText(client.getClientName());
        clientID.setText("ClientID: " + Integer.toString(client.getClientID()));
        deliveryDate.setText("Delivery date: " + order.getDeliveryTime());
        orderID.setText("Order ID: " + Integer.toString(order.getOrderID()));
        orderSum.setText("Ordersum: " + Integer.toString(order.getOrderSum()) + " kr");
        contactPerson.setText(client.getContactPerson());
        contactNumber.setText("Contactnumber: " + client.getContactNumber());
        email.setText("Email: " + client.getContactEmail());
        address.setText(client.getClientAdress());
        zipCode.setText(client.getClientZipCode() + " " + client.getClientCity());

        deliveredToggleBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int currentNumberOfOrders = shared.getInt("CURRENT_NUMBER_OF_ORDERS", 10);
                int numberOfOrders = shared.getInt("NUMBER_OF_ORDERS", 10);
                SharedPreferences.Editor editor = shared.edit();

                if (initialOrderStatus == 0 && isChecked && currentNumberOfOrders>0)
                    editor.putInt("CURRENT_NUMBER_OF_ORDERS", currentNumberOfOrders - 1);
                else if(initialOrderStatus == 1 && !isChecked && currentNumberOfOrders<numberOfOrders)
                    editor.putInt("CURRENT_NUMBER_OF_ORDERS", currentNumberOfOrders + 1);

                editor.commit();
                dbHelper.updateDelivered(order);
                requestPermission();
            }
        });

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

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1:
                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    smsHelper.sendSMS(dbHelper.getOrder(order.getOrderID()).getDelivered());
                }
                break;

            default:
                break;
        }
    }

    private void requestPermission() {
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, 1);
        } else {
            smsHelper.sendSMS(dbHelper.getOrder(order.getOrderID()).getDelivered());
            Log.i("DELIVERED",""+dbHelper.getOrder(order.getOrderID()).getDelivered());
        }
    }
}
