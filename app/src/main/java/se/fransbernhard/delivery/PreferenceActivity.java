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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class PreferenceActivity extends AppCompatActivity {

    private CheckBox sendConfirmation;
    private EditText editPhoneNr, editEmail;
    private TextView textAmountOfOrders;
    private SeekBar seekBarHowMany;
    private SharedPreferences shared;
    private int amountOfOrders;
    private Toolbar toolbar;
    private String number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_preference);

        shared = getSharedPreferences("PREFERENCES",MODE_PRIVATE);
        sendConfirmation = (CheckBox)findViewById(R.id.sendConfirmation);
        textAmountOfOrders = (TextView)findViewById(R.id.textAmountOfOrders);
        editPhoneNr = (EditText)findViewById(R.id.editPhoneNr);
        editEmail = (EditText)findViewById(R.id.editEmail);
        seekBarHowMany = (SeekBar)findViewById(R.id.seekBarHowMany);
        amountOfOrders = 10;
        toolbar = (Toolbar) findViewById(R.id.myToolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle(getResources().getString(R.string.settings_name));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setupSeekBar();

        setupAsSaved();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_preference_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.listButton:
                startActivity(new Intent(this, ListActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void clickedSave(View v) {
        SharedPreferences.Editor editor = shared.edit();
        editor.putString("PHONE_NUMBER", editPhoneNr.getText().toString());
        editor.putString("EMAIL",editEmail.getText().toString());
        editor.putInt("NUMBER_OF_ORDERS", amountOfOrders);
        editor.putInt("CURRENT_NUMBER_OF_ORDERS", amountOfOrders);
        editor.putBoolean("SEND_INFORMATION",sendConfirmation.isChecked());
        editor.commit();

        Toast.makeText(this, R.string.changesSaved,
                Toast.LENGTH_SHORT).show();
    }

    private void setupSeekBar() {
        seekBarHowMany.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                amountOfOrders = progress+10;
                textAmountOfOrders.setText(Integer.toString(amountOfOrders));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        seekBarHowMany.setMax(20);
    }

    private void setupAsSaved() {
        if (shared.contains("PHONE_NUMBER"))
            editPhoneNr.setText(shared.getString("PHONE_NUMBER", null));
        if (shared.contains("EMAIL"))
            editEmail.setText(shared.getString("EMAIL", null));
        if (shared.contains("NUMBER_OF_ORDERS")) {
            amountOfOrders = shared.getInt("NUMBER_OF_ORDERS", 10);
            seekBarHowMany.setProgress(shared.getInt("NUMBER_OF_ORDERS",10)-10);
            textAmountOfOrders.setText(Integer.toString(amountOfOrders));
        }
        if (shared.contains("SEND_INFORMATION"))
            sendConfirmation.setChecked(shared.getBoolean("SEND_INFORMATION", false));
    }


}
