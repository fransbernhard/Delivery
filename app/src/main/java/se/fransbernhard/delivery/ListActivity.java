package se.fransbernhard.delivery;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    private ListView myList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        myList = (ListView)findViewById(R.id.OrderLista);

        final ArrayList<Clients> kundLista = Clients.getKunderFromFile("kunder.json", this);
        ClientsAdapter adapter = new ClientsAdapter(this, kundLista);
        myList.setAdapter(adapter);
    }
}
