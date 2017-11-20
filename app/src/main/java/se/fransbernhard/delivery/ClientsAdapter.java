package se.fransbernhard.delivery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;


public class ClientsAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<Clients> mDataSource;


    public ClientsAdapter(Context context, ArrayList<Clients> items) {
        mContext = context;
        mDataSource = items;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mDataSource.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataSource.get(position);
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
        Clients client = (Clients) getItem(position);

        titleTextView.setText(client.name);
        subtitleTextView.setText(client.adress);
        detailTextView.setText(client.deliveryDate);


        return rowView;
    }
}
