package com.frontend.cj_app.common.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.frontend.cj_app.R;
import com.frontend.cj_app.common.payload.Coury_map;

import java.util.ArrayList;
import java.util.List;

public class MapListAdapter extends BaseAdapter {
    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<Coury_map> recv_addrs;

    public MapListAdapter(Context context, ArrayList<Coury_map> data) {
        mContext = context;
        recv_addrs = data;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return recv_addrs.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Coury_map getItem(int position) {
        return recv_addrs.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.delivery_map_addr_item, parent, false);

        TextView recv_name = (TextView)view.findViewById(R.id.recv_name);
        TextView recv_addr = (TextView)view.findViewById(R.id.recv_addr);
        TextView coury_condition = (TextView)view.findViewById(R.id.coury_condition);

        recv_name.setText(recv_addrs.get(position).getCouryToName());
        recv_addr.setText(recv_addrs.get(position).getCouryToAddress());
        coury_condition.setText(recv_addrs.get(position).getCouryCondition());
        return view;
    }
}
