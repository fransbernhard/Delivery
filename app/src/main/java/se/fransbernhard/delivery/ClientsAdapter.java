package se.fransbernhard.delivery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class ClientsAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater mInflater;
    private List<Client> clientData;
    private List<Order> orderData;

    /**
     * Constructor
     * @param context the context that you are in
     * @param clients a list of all clients
     * @param orders a list of all orders
     */
    public ClientsAdapter(Context context, List<Client> clients, List<Order> orders) {
        this.context = context;
        clientData = clients;
        orderData = orders;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /**
     * @return an int that represents the size of order list
     */
    @Override
    public int getCount() {
        return orderData.size();
    }

    /**
     * @return the Object that user has clicked
     */
    @Override
    public Object getItem(int position) {
        return clientData.get(position);
    }

    /**
     * @return the Object id that user has clicked
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * @param position the position that user has clicked on
     * @param convertView View
     * @param parent ViewGroup parent
     * @return the Object that user has clicked
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = mInflater.inflate(R.layout.list_item_clients, parent, false);
        TextView titleTextView = rowView.findViewById(R.id.client_list_name);
        TextView subtitleTextView = rowView.findViewById(R.id.client_list_address);
        TextView zipAndCityTextView = rowView.findViewById(R.id.client_list_zipAndCity);
        TextView detailTextView = rowView.findViewById(R.id.client_list_date);
        ImageView thumbnailImageView = rowView.findViewById(R.id.client_list_thumbnail);
        Order order = orderData.get(position);
        Client client = clientData.get(order.getClientID()-1);
        String clientZipAndCity = client.getClientZipCode() + " " + client.getClientCity();

        titleTextView.setText(client.getClientName());
        subtitleTextView.setText(client.getClientAdress());
        zipAndCityTextView.setText(clientZipAndCity);
        detailTextView.setText(order.getDeliveryTime());
        thumbnailImageView.setImageResource(R.drawable.map_icon);

        return rowView;
    }
}
