package com.aminmemariani.apps.bmicalculator;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.aminmemariani.apps.bmicalculator.Adapter.BmiAdapter;
import com.aminmemariani.apps.bmicalculator.dataModel.Bmi;

import java.util.ArrayList;
import java.util.List;
public class History extends AppCompatActivity {
    public Bmi b;
    public Bmi selectedB;
    public List<Bmi> BmiList;
    public ArrayList<Bmi> BmiArrayList;
    public BmiAdapter bmiAdapter;
    public ListView listView;
    public Dialog d;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);
        BmiArrayList = new ArrayList<>();
        b= new Bmi();
        listView = (ListView) findViewById(R.id.bmi_list);
        BmiList = b.listAll(Bmi.class);
        if (BmiList.isEmpty()){
            Toast.makeText(getApplicationContext(),"No Record",Toast.LENGTH_SHORT).show();
        }else{
            for(int i=0; i<BmiList.size();i++){
                b = BmiList.get(i);
                BmiArrayList.add(b);
            }
            bmiAdapter = new BmiAdapter(History.this, BmiArrayList);
            listView.setAdapter(bmiAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    d = new Dialog(History.this);
                    d.setContentView(R.layout.history_detail);
                    d.setCanceledOnTouchOutside(true);
                    selectedB = (Bmi) parent.getItemAtPosition(position);
                    TextView date = (TextView) d.findViewById(R.id.date_and_time);
                    date.setText(selectedB.getDate());
                    TextView bmiD = (TextView) d.findViewById(R.id.bmi_detail);
                    bmiD.setText(selectedB.getBMI());
                    d.show();
                }
            });
            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    d = new Dialog(History.this);
                    d.setContentView(R.layout.delete_item);
                    d.setCanceledOnTouchOutside(true);
                    selectedB = (Bmi) parent.getItemAtPosition(position);
                    d.show();
                    return true;
                }
            });
        }
    }
    public void deleteAll(View v){
        Bmi.deleteAll(Bmi.class);
        BmiArrayList.clear();
        BmiArrayList.addAll(Bmi.listAll(Bmi.class));
        bmiAdapter.notifyDataSetInvalidated();
        listView.invalidateViews();
        listView.refreshDrawableState();
        Toast.makeText(getApplicationContext(),"History Cleared Successfully",Toast.LENGTH_SHORT).show();
    }
    public void ok(View v){
        d.hide();
    }
    public void deleteItem(View v){
        selectedB = Bmi.findById(Bmi.class, selectedB.getId());
        selectedB.delete();
        BmiArrayList.clear();
        BmiArrayList.addAll(Bmi.listAll(Bmi.class));
        bmiAdapter.notifyDataSetChanged();
        listView.invalidateViews();
        listView.refreshDrawableState();
        d.hide();
    }
}
