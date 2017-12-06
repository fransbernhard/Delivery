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
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class DetailActivity extends AppCompatActivity implements OnMapReadyCallback {

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
    private GoogleMap mMap;
    private LatLng latlong;
    private String number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        toolbar = (Toolbar) findViewById(R.id.myToolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // This will remove App name
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
        shared = getSharedPreferences("PREFERENCES",MODE_PRIVATE);
        dbHelper = new DBHelper(this);
        Intent intent = getIntent();
        number = intent.getStringExtra("PHONE_NUMBER");
        order = dbHelper.getOrder(intent.getIntExtra("ORDER_ID", 1));
        client = dbHelper.getClient(order.getClientID());
        deliveredToggleBtn = (ToggleButton)findViewById(R.id.DeliveredToggleBtn);
        initialOrderStatus = order.delivered;
        deliveredToggleBtn.setChecked(initialOrderStatus==0);
        smsHelper = new SMSHelper(number, order.getOrderID());

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        clientName = findViewById(R.id.clientName);
        clientID = findViewById(R.id.clientID);
        deliveryDate = findViewById(R.id.deliveryDate);
        orderID = findViewById(R.id.orderID);
        orderSum = findViewById(R.id.orderSum);
        contactPerson = findViewById(R.id.contactPerson);
        contactNumber = findViewById(R.id.contactNumber);
        email = findViewById(R.id.contactEmail);
        address = findViewById(R.id.clientAddress);
        zipCode = findViewById(R.id.clientZipCode);

        clientName.setText(client.getClientName());
        clientID.setText(getResources().getText(R.string.clientID) + " " + Integer.toString(client.getClientID()));
        deliveryDate.setText(getResources().getText(R.string.deliveryDateField) + " " + order.getDeliveryTime());
        orderID.setText(getResources().getText(R.string.orderID) + " " + Integer.toString(order.getOrderID()));
        orderSum.setText(getResources().getText(R.string.orderSum) + " " + Integer.toString(order.getOrderSum()) + " kr");
        contactPerson.setText(client.getContactPerson());
        contactNumber.setText(client.getContactNumber());
        email.setText(client.getContactEmail());
        address.setText(client.getClientAdress());
        zipCode.setText(client.getClientZipCode() + " " + client.getClientCity());

        deliveredToggleBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int currentNumberOfOrders = shared.getInt("CURRENT_NUMBER_OF_ORDERS", 10);
                int numberOfOrders = shared.getInt("NUMBER_OF_ORDERS", 10);
                SharedPreferences.Editor editor = shared.edit();

                if (initialOrderStatus == 0 && !isChecked && currentNumberOfOrders>0)
                    editor.putInt("CURRENT_NUMBER_OF_ORDERS", currentNumberOfOrders - 1);
                else if(initialOrderStatus == 0 && isChecked && currentNumberOfOrders<numberOfOrders)
                    editor.putInt("CURRENT_NUMBER_OF_ORDERS", currentNumberOfOrders + 1);

                editor.commit();
                dbHelper.updateDelivered(order);
                if (!isChecked)
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
                    smsHelper.sendSMS();
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
            smsHelper.sendSMS();
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        latlong = new LatLng(client.getClientLat(), client.getClientLong());
        mMap.addMarker(new MarkerOptions().position(latlong).title(client.getClientName()));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlong,15));
        mMap.animateCamera(CameraUpdateFactory.zoomIn());
        mMap.animateCamera( CameraUpdateFactory.zoomTo(15), 2000, null);
    }
}

