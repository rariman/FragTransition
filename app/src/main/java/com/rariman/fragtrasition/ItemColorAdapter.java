package com.rariman.fragtrasition;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class ItemColorAdapter extends BaseAdapter {

    private ItemColor[] data;
    private LayoutInflater inflater;

    public ItemColorAdapter(Context context, ItemColor[] data)
    {
        this.inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public Object getItem(int position) {
        return data[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ItemColorHolder itemColorHolder;

        if (view == null){
            view = inflater.inflate(R.layout.itemviewlayout, parent, false);
            itemColorHolder = new ItemColorHolder();
            itemColorHolder.view = view.findViewById(R.id.itemview);
            view.setTag(itemColorHolder);
        }
        else{
            itemColorHolder = (ItemColorHolder)view.getTag();
        }

        itemColorHolder.view.setBackgroundColor(data[position].getColor());

        return view;
    }

    static class ItemColorHolder
    {
        View view;
    }
}