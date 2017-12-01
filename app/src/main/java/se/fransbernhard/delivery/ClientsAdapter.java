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


    public ClientsAdapter(Context context, List<Client> clients, List<Order> orders) {
        this.context = context;
        clientData = clients;
        orderData = orders;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return orderData.size();
    }

    @Override
    public Object getItem(int position) {
        return clientData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = mInflater.inflate(R.layout.list_item_clients, parent, false);
        TextView titleTextView = (TextView) rowView.findViewById(R.id.client_list_title);
        TextView subtitleTextView = (TextView) rowView.findViewById(R.id.client_list_subtitle);
        TextView detailTextView = (TextView) rowView.findViewById(R.id.client_list_detail);
        ImageView thumbnailImageView = (ImageView) rowView.findViewById(R.id.client_list_thumbnail);
        Order order = orderData.get(position);
        Client client = clientData.get(order.getClientID()-1);

        titleTextView.setText(client.getClientName());
        subtitleTextView.setText(client.getClientAdress());
        detailTextView.setText(order.getDeliveryTime());
        thumbnailImageView.setImageResource(R.drawable.icon_two);

        return rowView;
    }
}
