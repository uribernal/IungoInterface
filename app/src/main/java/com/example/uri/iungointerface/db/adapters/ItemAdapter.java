package com.example.uri.iungointerface.db.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.uri.iungointerface.R;
import com.example.uri.iungointerface.db.classes.Item;


/**
 * Created by Uri on 06/01/2017.
 */

public class ItemAdapter extends ArrayAdapter<Item> {

    Context myContext;
    int myLayoutResourceID;
    Item myItems[] = null;

    public ItemAdapter(Context context, int myLayoutResourceID, Item[] myItems) {
        super(context, myLayoutResourceID, myItems);

        this.myContext = context;
        this.myLayoutResourceID = myLayoutResourceID;
        this.myItems = myItems;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ItemAdapter.ItemHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService( Context.LAYOUT_INFLATER_SERVICE );
            row = inflater.inflate(myLayoutResourceID, parent, false);

            holder = new ItemAdapter.ItemHolder();
            holder.picture = (ImageView) row.findViewById(R.id.item_image);
            holder.name = (TextView) row.findViewById(R.id.item_name);

            row.setTag(holder);

        } else {
            holder = (ItemAdapter.ItemHolder) row.getTag();
        }

        Item item = myItems[position];
        holder.picture.setImageResource(item.getResource());
        holder.name.setText(item.getName());


        return row;
    }


    static class ItemHolder {
        ImageView picture;
        TextView name;
    }
}
