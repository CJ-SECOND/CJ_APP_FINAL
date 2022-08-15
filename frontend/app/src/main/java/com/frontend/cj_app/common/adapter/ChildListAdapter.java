package com.frontend.cj_app.common.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.frontend.cj_app.R;
import com.frontend.cj_app.common.model.TASK_CHILD_LIST;

import java.util.ArrayList;

public class ChildListAdapter extends BaseAdapter {
    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<TASK_CHILD_LIST> task_child;

    public ChildListAdapter(Context context, ArrayList<TASK_CHILD_LIST> data) {
        mContext = context;
        task_child = data;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return task_child.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public TASK_CHILD_LIST getItem(int position) {
        return task_child.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.task_request_child_list, parent, false);

        TextView area = (TextView)view.findViewById(R.id.area);
        TextView datetime = (TextView)view.findViewById(R.id.datetime);
        TextView timeState = (TextView)view.findViewById(R.id.timeState);
        area.setText(task_child.get(position).getArea());
        datetime.setText(task_child.get(position).getDatetime());
        timeState.setText(task_child.get(position).getTimeState());
        return view;
    }
}
