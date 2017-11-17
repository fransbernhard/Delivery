package se.fransbernhard.delivery;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

        setupSeekBar();

        setupAsSaved();

    }

    public void clickedSave(View v) {
        SharedPreferences.Editor editor = shared.edit();
        editor.putString("PHONE_NUMBER",editPhoneNr.getText().toString());
        editor.putString("EMAIL",editEmail.getText().toString());
        editor.putInt("NUMBER_OF_ORDERS", amountOfOrders);
        editor.putBoolean("SEND_INFORMATION",sendConfirmation.isChecked());
        editor.commit();

        Toast.makeText(this, "Inställningar sparade",
                Toast.LENGTH_SHORT).show();
    }

    private void setupSeekBar() {
        seekBarHowMany.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                amountOfOrders = progress;
                textAmountOfOrders.setText(Integer.toString(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBarHowMany.setMin(10);
        seekBarHowMany.setMax(50);
    }

    private void setupAsSaved() {
        if (shared.contains("PHONE_NUMBER"))
            editPhoneNr.setText(shared.getString("PHONE_NUMBER", null));
        if (shared.contains("EMAIL"))
            editEmail.setText(shared.getString("EMAIL", null));
        if (shared.contains("NUMBER_OF_ORDERS")) {
            amountOfOrders = shared.getInt("NUMBER_OF_ORDERS", 10);
            seekBarHowMany.setProgress(shared.getInt("NUMBER_OF_ORDERS",10));
            textAmountOfOrders.setText(Integer.toString(amountOfOrders));
        }
        if (shared.contains("SEND_INFORMATION"))
            sendConfirmation.setChecked(shared.getBoolean("SEND_INFORMATION", false));
    }

}
