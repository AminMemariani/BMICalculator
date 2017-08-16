package com.aminmemariani.apps.bmicalculator.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.aminmemariani.apps.bmicalculator.R;
import com.aminmemariani.apps.bmicalculator.dataModel.Bmi;

import java.util.ArrayList;


public class BmiAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<Bmi> BmiList;

    public BmiAdapter(Context context, ArrayList<Bmi> bmiList) {
        this.context = context;
        this.BmiList = bmiList;
    }

    @Override
    public int getCount() {
        return BmiList.size();
    }

    @Override
    public Object getItem(int position) {
        return BmiList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = View.inflate(context, R.layout.bmi_item,null);
        }
        TextView BmiValue = (TextView) convertView.findViewById(R.id.txt_item_bmi);
        //TextView BmiDate = (TextView) convertView.findViewById(R.id.txt_item_date);
        Bmi bmi = BmiList.get(position);
        BmiValue.setText(bmi.getBMI());
        //BmiDate.setText(bmi.getDate());
        return convertView;
    }
}
